package com.wdzdeng.it.friend.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdzdeng.it.friend.common.SystemConstant;
import com.wdzdeng.it.friend.dao.UserResultMapper;
import com.wdzdeng.it.friend.entity.BaseUser;
import com.wdzdeng.it.friend.entity.UserResult;
import com.wdzdeng.it.friend.exception.SaveAnswerException;
import com.wdzdeng.it.friend.service.IBaseUserService;
import com.wdzdeng.it.friend.service.IMatchService;
import com.wdzdeng.it.friend.service.IUserResultService;
import com.wdzdeng.it.friend.util.MatchThreadPoolUtil;
import com.wdzdeng.it.friend.vo.RankVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.TreeSet;

/**
 * @author wdz
 * @version 1.0
 * @date 2019/12/28 23:11
 */
@Primary
@Service
@EnableScheduling
public class UserResultServiceImpl extends ServiceImpl<UserResultMapper, UserResult> implements IUserResultService {

    private static final int FIXED_RATE = 6*1000*5;
    @Autowired
    IBaseUserService baseUserService;
    @Autowired
    IMatchService matchService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Integer userId, String answer) throws SaveAnswerException {
        UserResult result = new UserResult()
                .setResult(answer)
                .setUserId(userId);
        if (
                ! baseUserService.update(Wrappers
                .<BaseUser>lambdaUpdate()
                .eq(BaseUser::getUserId, userId).eq(BaseUser::getPersonal,1)
                .eq(BaseUser::getAnswer, 0).set(BaseUser::getAnswer, 1) ) || !save(result)
        ){
            throw new SaveAnswerException();
        }
        MatchThreadPoolUtil.match(()-> matchWhenInsert(userId, answer));
    }
    @Override
    @Scheduled(fixedDelay = FIXED_RATE)
    public synchronized void timeTaskForUpdateMatch(){
        Page<UserResult> page = new Page<>(1, SystemConstant.PAGE_SIZE);
        IPage<UserResult> firstPage = page(page,Wrappers
                .<UserResult>lambdaQuery()
                .orderByAsc(UserResult::getUserId)
        );
        if (null == firstPage || firstPage.getRecords().size() == 0){
            return;
        }
        for(UserResult userResult : firstPage.getRecords()){
            updateMatch(userResult);
        }
        for (int pageNo = 2; pageNo <= firstPage.getPages(); ++pageNo){
            Page<UserResult> pageSet = new Page<>(pageNo, SystemConstant.PAGE_SIZE);
            IPage<UserResult> otherPage = page(pageSet,Wrappers
                    .<UserResult>lambdaQuery()
                    .orderByAsc(UserResult::getUserId)
            );
            if (null == otherPage || otherPage.getRecords().size() == 0){
                return;
            }
            for(UserResult userResult : otherPage.getRecords()){
                updateMatch(userResult);
            }
        }

    }

    private void updateMatch(UserResult userResult){
        if (null == userResult){
            return;
        }
        Page<UserResult> currentPage = new Page<>(1, SystemConstant.PAGE_SIZE);
        IPage<UserResult> userResultPage = page(currentPage,Wrappers
                .<UserResult>lambdaQuery()
                .ne(UserResult::getUserId, userResult.getUserId())
                .gt(UserResult::getUserId,userResult.getLastId())
                .orderByAsc(UserResult::getUserId)
        );
        if (userResultPage == null || userResultPage.getRecords().size() == 0){
            return;
        }
        List<UserResult> otherUserResults = userResultPage.getRecords();
        TreeSet<RankVO> rankVoTreeSet = JSON.parseObject(userResult.getMatchSet(), new TypeReference<TreeSet<RankVO>>(){});
        UserResult result = new UserResult()
                .setUserId(userResult.getUserId())
                .setMatchSet(matchService.compare(rankVoTreeSet,userResult.getResult(),otherUserResults))
                .setLastId(otherUserResults.get(otherUserResults.size()-1).getUserId());
        updateById(result);

    }

    private void matchWhenInsert(Integer userId,String answer){
        Page<UserResult> currentPage = new Page<>(1, SystemConstant.PAGE_SIZE);
        IPage<UserResult> userResultPage = page(currentPage,Wrappers
                .<UserResult>lambdaQuery()
                .ne(UserResult::getUserId, userId)
                .orderByAsc(UserResult::getUserId)
        );
        if (userResultPage == null || userResultPage.getRecords().size() == 0){
            return;
        }
        List<UserResult> userResults = userResultPage.getRecords();
        UserResult result = new UserResult()
                .setUserId(userId)
                .setMatchSet(matchService.matchFirst(userResults, answer))
                .setLastId(userResults.get(userResults.size()-1).getUserId());
        updateById(result);
    }

}
