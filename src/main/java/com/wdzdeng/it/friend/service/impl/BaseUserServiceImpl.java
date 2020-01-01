package com.wdzdeng.it.friend.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdzdeng.it.friend.common.DealResult;
import com.wdzdeng.it.friend.common.SystemConstant;
import com.wdzdeng.it.friend.common.entity.WeichatResp;
import com.wdzdeng.it.friend.dao.BaseUserMapper;
import com.wdzdeng.it.friend.entity.BaseUser;
import com.wdzdeng.it.friend.exception.CodeEmptyException;
import com.wdzdeng.it.friend.exception.UserLoginException;
import com.wdzdeng.it.friend.service.IBaseUserService;
import com.wdzdeng.it.friend.util.HttpUtil;
import com.wdzdeng.it.friend.vo.BaseUserVO;
import org.junit.platform.commons.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author wdz
 * @version 1.0
 * @date 2019/12/28 23:08
 */
@Primary
@Service
public class BaseUserServiceImpl extends ServiceImpl<BaseUserMapper, BaseUser> implements IBaseUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseUserServiceImpl.class);
    @Override
    public DealResult<BaseUserVO> silentLogin(String code) throws CodeEmptyException, UserLoginException {
        LOGGER.info("登陆请求: {}", code);
        if(StringUtils.isBlank(code)){
           throw new CodeEmptyException();
        }
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + SystemConstant.APP_ID +"&secret=" + SystemConstant.SECRET + "&js_code=" + code + "&grant_type=authorization_code";
        String respone = HttpUtil.get(url);
        WeichatResp userInfoRespVO = JSON.parseObject(respone,WeichatResp.class);
        if(org.apache.commons.lang3.StringUtils.isBlank(userInfoRespVO.getOpenId())){
            LOGGER.error("小程序登录错误,请求code: {},错误信息: {}", code, userInfoRespVO.getErrCode() + ":" + userInfoRespVO.getErrMsg());
            throw new UserLoginException();
        }
        BaseUser loginUser = getById(userInfoRespVO.getOpenId());
        if (null == loginUser){
            return DealResult.fail(new BaseUserVO().setOpenId(userInfoRespVO.getOpenId()));
        }
        return DealResult.ok(new BaseUserVO()
                .setOpenId(loginUser.getOpenId())
                .setCity(loginUser.getCity())
                .setProvince(loginUser.getProvince())
                .setUserId(loginUser.getUserId())
                .setGender(loginUser.getGender())
                .setHeadUrl(loginUser.getHeadUrl())
                .setNickName(loginUser.getNickName())
        );
    }

    @Override
    public BaseUserVO login(BaseUser baseUser) throws UserLoginException {
        if (!save(baseUser)){
            throw new UserLoginException();
        }
        BaseUser currentBaseUser = getById(baseUser.getOpenId());
        return  new BaseUserVO()
                .setUserId(currentBaseUser.getUserId())
                .setOpenId(currentBaseUser.getOpenId())
                .setCity(currentBaseUser.getCity())
                .setProvince(currentBaseUser.getProvince())
                .setNickName(currentBaseUser.getNickName())
                .setGender(currentBaseUser.getGender())
                .setHeadUrl(currentBaseUser.getHeadUrl());
    }
}
