package com.wdzdeng.it.friend.common.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author wdz
 * @version 1.0
 * @date 2019/12/29 14:48
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WeichatResp implements Serializable {

    @JSONField(name="openid")
    private String openId;

    @JSONField(name="session_key")
    private String sessionKey;

    @JSONField(name="unionid")
    private String unionId;

    @JSONField(name="errcode")
    private String errCode;

    @JSONField(name="errmsg")
    private String errMsg;
}
