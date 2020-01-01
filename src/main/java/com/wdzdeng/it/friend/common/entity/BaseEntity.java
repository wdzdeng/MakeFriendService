package com.wdzdeng.it.friend.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 实例类基类
 *
 * @author zzj
 * @version 2018/12/27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BaseEntity<T> implements Serializable {

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    /**
     * 修改时间
     */
    @TableField(value = "MOD_TIME", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modTime;



    /**
     * 删除标识（0-正常,1-删除）
     */
    @TableField(value = "DEL_FLAG", fill = FieldFill.INSERT)
    @TableLogic
    private Short delFlag = 0;

    /**
     * 备注信息
     */
    @TableField("REMARKS")
    private String remarks;

}
