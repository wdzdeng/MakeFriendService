package com.wdzdeng.it.friend.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wdzdeng.it.friend.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author wdz
 * @version 1.0
 * @date 2019/12/28 21:33
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "base_user")
public class BaseUser extends BaseEntity<BaseUser> implements Serializable {
    @TableField(value = "OPEN_ID")
    @TableId
    @NotNull(message = "openId不为null")
    private String openId;

    @TableField(value = "USER_ID")
    private Integer userId;

    @TableField(value = "NICK_NAME")
    @NotNull(message = "微信昵称为null")
    private String nickName;

    @TableField(value = "GENDER")
    @NotNull(message = "性别为null")
    private Short gender;

    @TableField(value = "PROVINCE")
    private String province;

    @TableField(value = "CITY")
    private String city;

    @TableField(value = "HEAD_URL")
    @NotNull(message = "头像为null")
    private String headUrl;

    @TableField(value = "ANSWER")
    private Short answer;

    @TableField(value = "PERSONAL")
    private Short personal;
}
