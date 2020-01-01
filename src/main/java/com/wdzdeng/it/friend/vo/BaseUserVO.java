package com.wdzdeng.it.friend.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author wdz
 * @version 1.0
 * @date 2019/12/29 18:35
 */
@Data
@Accessors(chain = true)
public class BaseUserVO implements Serializable {

    private String openId;

    private Integer userId;

    private String nickName;

    private Short gender;

    private String province;

    private String city;

    private String headUrl;
}
