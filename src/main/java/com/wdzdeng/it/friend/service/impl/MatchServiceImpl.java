package com.wdzdeng.it.friend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wdzdeng.it.friend.entity.UserInfo;
import com.wdzdeng.it.friend.entity.UserResult;
import com.wdzdeng.it.friend.service.IMatchService;
import com.wdzdeng.it.friend.service.IUserInfoService;
import com.wdzdeng.it.friend.vo.RankVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TreeSet;

/**
 * @author wdz
 * @version 1.0
 * @date 2019/12/30 21:12
 */
@Primary
@Service
public class MatchServiceImpl implements IMatchService {
    @Autowired
    IUserInfoService userInfoService;

    @Override
    public String matchFirst(List<UserResult> userResults, String answer){
        TreeSet<RankVO> rankVoSet = new TreeSet<>();
        return compare(rankVoSet,answer, userResults);
    }
    @Override
    public  String compare(TreeSet<RankVO> rankVoSet,String currentAnswer, List<UserResult> userResults){
        int step = 100 / currentAnswer.length();
        int match = 0;
        StringBuilder stringBuffer = new StringBuilder();
        for (UserResult result: userResults){
            UserInfo userInfo = userInfoService.getById(result.getUserId());
            if (null == userInfo){
                continue;
            }
            String otherResult = result.getResult();
            for (int i = 0; i < otherResult.length(); ++i){
                stringBuffer.append(i+1);
                stringBuffer.append(currentAnswer.charAt(i));
                stringBuffer.append(otherResult.charAt(i));
                if (currentAnswer.charAt(i) == otherResult.charAt(i)){
                    match += step;
                }

            }
            RankVO rankVo = new RankVO()
                    .setUserId(userInfo.getUserId())
                    .setGender(userInfo.getGender())
                    .setMatch(match)
                    .setName(userInfo.getName())
                    .setAnswerCompare(stringBuffer.toString());
            insertToSet(rankVoSet, rankVo);
        }
        return JSONObject.toJSONString(rankVoSet);
    }
    private  void insertToSet(TreeSet<RankVO> rankVOSet,RankVO rankVO){
        if (rankVOSet.size() <= 20) {
            rankVOSet.add(rankVO);
            return;
        }
        if (rankVOSet.last().compareTo(rankVO) > 0){
            rankVOSet.pollLast();
            rankVOSet.add(rankVO);
        }

    }

}
