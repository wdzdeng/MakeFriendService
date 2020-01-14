package com.wdzdeng.it.friend.web;

import com.wdzdeng.it.friend.common.R;
import com.wdzdeng.it.friend.entity.UserInfo;
import com.wdzdeng.it.friend.exception.SaveUserInfoException;
import com.wdzdeng.it.friend.service.IUserInfoService;
import com.wdzdeng.it.friend.vo.UserInfoVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author wdz
 * @version 1.0
 * @date 2019/12/29 20:22
 */
@RestController
@RequestMapping(value = "/info", produces = "application/json;charset=UTF-8")
public class UserInfoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);
    @Autowired
    private IUserInfoService userInfoService;

    @GetMapping(value = "/query")
    public R<UserInfoVO> getUserInfo(@RequestParam("userId") Integer userId){
        if (null == userId){
            return R.failed("用户id不合法");
        }
        UserInfo userInfo = userInfoService.getById(userId);
        if(null == userInfo){
           return R.failed("个人信息尚未完善");
        }
        return R.ok(convertToUserInfoVO(userInfo));
    }

    @GetMapping(value = "/query/other")
    public R<UserInfoVO> getOtherUserInfo(@RequestParam("otherUserId") Integer otherUserId,
                                          @RequestParam("match") Integer match){
        if ( null == otherUserId || null == match){
            return R.failed("请求参数不合法");
        }
        UserInfo otherUserInfo = userInfoService.getById(otherUserId);
        if (null == otherUserInfo){
            return R.failed("对方用户不存在");
        }
        if (otherUserInfo.getExpectMatch() > match){
            return R.failed("低于对方期望匹配度，无权查看");
        }
        return R.ok(convertToUserInfoVO(otherUserInfo));
    }


    @PostMapping(value = "/save")
    public R<Boolean> saveUserInfo(@Validated @RequestBody UserInfo userInfo, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return R.failed(bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            userInfoService.mySaveOrUpdate(userInfo);
        } catch (SaveUserInfoException e) {
            LOGGER.error(e.getMessage());
            return R.failed(e.getMessage());
        }
        return R.ok(Boolean.TRUE);
    }


    private UserInfoVO convertToUserInfoVO(UserInfo userInfo){
        if(null == userInfo){
            return new UserInfoVO();
        }
        return new UserInfoVO()
                .setBirthday(userInfo.getBirthday())
                .setCity(userInfo.getCity())
                .setExpectCapYear(userInfo.getExpectCapYear())
                .setGender(userInfo.getGender())
                .setName(userInfo.getName())
                .setProvince(userInfo.getProvince())
                .setQq(userInfo.getQq())
                .setSignature(userInfo.getSignature())
                .setUserId(userInfo.getUserId())
                .setWeichat(userInfo.getWeichat())
                .setExpectCity(userInfo.getExpectCity())
                .setExpectGender(userInfo.getExpectGender())
                .setExpectLowerYear(userInfo.getExpectLowerYear())
                .setExpectMatch(userInfo.getExpectMatch())
                .setExpectProvince(userInfo.getExpectProvince());
    }
}
