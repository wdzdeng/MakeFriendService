package com.wdzdeng.it.friend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wdzdeng.it.friend.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author wdz
 * @version 1.0
 * @date 2019/12/28 22:49
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "user_result")
public class UserResult extends BaseEntity<UserResult> {
    @TableField(value = "USER_ID")
    @TableId
    private Integer userId;

    @TableField(value = "RESULT")
    private String result;

    @TableField(value = "MATCH_SET")
    private String matchSet;

    @TableField(value = "LAST_ID")
    private Integer lastId;
}
