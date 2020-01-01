package com.wdzdeng.it.friend.web;


import com.wdzdeng.it.friend.common.DealResult;
import com.wdzdeng.it.friend.common.R;
import com.wdzdeng.it.friend.entity.BaseUser;
import com.wdzdeng.it.friend.exception.UserLoginException;
import com.wdzdeng.it.friend.service.IBaseUserService;
import com.wdzdeng.it.friend.vo.BaseUserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @Author: wdz
 * @Description: 用于小程序用户登录
 */
@RestController
@RequestMapping(value = "/user", produces = "application/json;charset=UTF-8")
public class UserLoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginController.class);
    @Autowired
    private IBaseUserService baseUserService;
    /**
     * 用户登录请求
     * @param
     * @return
     */
    @GetMapping(value = "/silent/login")
    public R<DealResult<BaseUserVO>> silentLogin(@RequestParam String code){
        DealResult<BaseUserVO> dealResult;
        try {
            dealResult = baseUserService.silentLogin(code);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return R.failed(e.getMessage());
        }
        return R.ok(dealResult);
    }

    @GetMapping(value = "/login")
    public R<BaseUserVO> login(@Validated @RequestBody BaseUser baseUser, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return R.failed(bindingResult.getFieldError().getDefaultMessage());
        }
        BaseUserVO userVO;
        try {
            userVO = baseUserService.login(baseUser);
        } catch (UserLoginException e) {
            LOGGER.error(e.getMessage());
            return R.failed(e.getMessage());
        }
        return R.ok(userVO);
    }

}
