package com.wdzdeng.it.friend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wdzdeng.it.friend.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wdz
 * @version 1.0
 * @date 2019/12/28 22:32
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "user_info")
public class UserInfo extends BaseEntity<UserInfo> implements Serializable {
    @TableField(value = "USER_ID")
    @TableId
    @NotNull(message = "Id不能为空")
    private Integer userId;

    @TableField(value = "NAME")
    @NotNull(message = "昵称不能为空")
    private String name;

    @TableField(value = "BIRTHDAY")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "生日不能为空")
    private Date birthday;

    @TableField(value = "GENDER")
    @NotNull(message = "性别不能为空")
    private Short gender;

    @TableField(value = "HEAD_URL")
    private String headUrl;

    @TableField(value = "PROVINCE")
    private String province;

    @TableField(value = "CITY")
    private String city;

    @TableField(value = "SIGNATURE")
    private String signature;

    @TableField(value = "WEICHAT")
    private String weichat;

    @TableField(value = "QQ")
    private String qq;

    @TableField(value = "EXPECT_PROVINCE")
    private String expectProvince;

    @TableField(value = "EXPECT_CITY")
    private String expectCity;

    @TableField(value = "EXPECT_GENDER")
    private Short expectGender;

    @TableField(value = "EXPECT_CAP_YEAR")
    private Integer expectCapYear;

    @TableField(value = "EXPECT_LOWER_YEAR")
    private Integer expectLowerYear;

    @TableField(value = "EXPECT_MATCH")
    private Integer expectMatch;
}
