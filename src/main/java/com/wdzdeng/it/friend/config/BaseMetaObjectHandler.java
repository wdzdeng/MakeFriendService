package com.wdzdeng.it.friend.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * mybatis-plus 字段自动填充
 *
 * @author wdz
 */
@Slf4j
@Component
public class BaseMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        final Object addTime = getFieldValByName("createTime", metaObject);
        final Object modTime = getFieldValByName("modTime", metaObject);
        final Object delFlag = getFieldValByName("delFlag", metaObject);

        if (addTime == null) {
            this.setFieldValByName("createTime", new Date(), metaObject);
        }
        if (modTime == null) {
            this.setFieldValByName("modTime", new Date(), metaObject);
        }
        if (delFlag == null) {
            this.setFieldValByName("delFlag", 0, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("modTime", new Date(), metaObject);
    }
}
