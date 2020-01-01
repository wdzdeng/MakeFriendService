package com.wdzdeng.it.friend.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdzdeng.it.friend.dao.UserInfoMapper;
import com.wdzdeng.it.friend.entity.BaseUser;
import com.wdzdeng.it.friend.entity.UserInfo;
import com.wdzdeng.it.friend.exception.SaveUserInfoException;
import com.wdzdeng.it.friend.service.IBaseUserService;
import com.wdzdeng.it.friend.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wdz
 * @version 1.0
 * @date 2019/12/28 23:09
 */
@Primary
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {
    @Autowired
    IBaseUserService baseUserService;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void mySaveOrUpdate(UserInfo userInfo) throws SaveUserInfoException {
        if (!baseUserService.update(Wrappers
                .<BaseUser>lambdaUpdate()
                .eq(BaseUser::getUserId, userInfo.getUserId())
                .set(BaseUser::getPersonal, 1)) || !saveOrUpdate(userInfo))
        {
            throw new SaveUserInfoException();
        }


    }
}
