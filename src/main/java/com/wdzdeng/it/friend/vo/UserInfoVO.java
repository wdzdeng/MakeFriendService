package com.wdzdeng.it.friend.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author wdz
 * @version 1.0
 * @date 2019/12/29 20:25
 */
@Data
@Accessors(chain = true)
public class UserInfoVO {

    private Integer userId;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private Short gender;

    private String province;

    private String city;

    private String signature;

    private String weichat;

    private String qq;

    private String expectProvince;

    private String expectCity;

    private Short expectGender;

    private Integer expectCapYear;

    private Integer expectLowerYear;

    private Integer expectMatch;
}
