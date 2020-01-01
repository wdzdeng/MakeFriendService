package com.wdzdeng.it.friend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wdzdeng.it.friend.entity.BaseUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wdz
 * @version 1.0
 * @date 2019/12/28 23:00
 */
@Mapper
public interface BaseUserMapper extends BaseMapper<BaseUser> {
}
