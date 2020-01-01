package com.wdzdeng.it.friend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wdzdeng.it.friend.entity.UserInfo;
import com.wdzdeng.it.friend.exception.SaveUserInfoException;

/**
 * @author wdz
 * @version 1.0
 * @date 2019/12/28 23:04
 */
public interface IUserInfoService extends IService<UserInfo> {
    void mySaveOrUpdate(UserInfo userInfo) throws SaveUserInfoException;
}
