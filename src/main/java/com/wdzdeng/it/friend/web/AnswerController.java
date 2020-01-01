package com.wdzdeng.it.friend.web;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wdzdeng.it.friend.common.R;
import com.wdzdeng.it.friend.entity.BaseUser;
import com.wdzdeng.it.friend.exception.SaveAnswerException;
import com.wdzdeng.it.friend.service.IBaseUserService;
import com.wdzdeng.it.friend.service.IUserResultService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wdz
 * @version 1.0
 * @date 2019/12/29 21:44
 */
@RestController
@RequestMapping(value = "/answer", produces = "application/json;charset=UTF-8")
public class AnswerController {
    private static final Integer ANSWER_LENGTH = 5;
    @Autowired
    IUserResultService resultService;
    @Autowired
    IBaseUserService baseUserService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginController.class);
    @PostMapping("/save")
    public R<Boolean> save(@RequestParam("userId") Integer userId, @RequestParam("answer") String answer){
        if(null == userId ){
            return R.failed("userId 不合法");
        }
        if(StringUtils.isBlank(answer) || answer.length() != ANSWER_LENGTH){
            return R.failed("answer 不合法");
        }
        try {
            resultService.save(userId, answer);
        } catch (SaveAnswerException e) {
            LOGGER.error(e.getMessage());
            return R.failed(e.getMessage());
        }
        return R.ok(true);
    }
    @GetMapping("/check")
    public R<Boolean> check(@RequestParam("userId") Integer userId){
        if(null == userId ){
            return R.failed("userId 不合法");
        }
        BaseUser baseUser = baseUserService.getOne(Wrappers
                .<BaseUser>lambdaQuery()
                .eq(BaseUser::getUserId,userId)
        );
        if(null == baseUser){
            return R.failed("用户不存在");
        }
        if (baseUser.getAnswer() == 1){
            return R.failed("你已经完成答题");
        }
        if (baseUser.getPersonal()==0){
            return R.failed("请完善个人信息");
        }
        return R.ok(true);
    }
}
