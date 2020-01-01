package com.wdzdeng.it.friend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wdzdeng.it.friend.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wdz
 * @version 1.0
 * @date 2019/12/28 23:01
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
