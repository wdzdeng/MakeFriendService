package com.wdzdeng.it.friend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wdzdeng.it.friend.common.DealResult;
import com.wdzdeng.it.friend.entity.BaseUser;
import com.wdzdeng.it.friend.exception.CodeEmptyException;
import com.wdzdeng.it.friend.exception.UserLoginException;
import com.wdzdeng.it.friend.vo.BaseUserVO;

/**
 * @author wdz
 * @version 1.0
 * @date 2019/12/28 23:03
 */
public interface IBaseUserService extends IService<BaseUser> {
    /**
     * 微信临时code 登陆
     * @param code
     * @return 在小程序中的id
     */
    DealResult<BaseUserVO> silentLogin(String code) throws CodeEmptyException, UserLoginException;

    BaseUserVO login(BaseUser baseUser) throws UserLoginException;
}
