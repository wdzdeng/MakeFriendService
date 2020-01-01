package com.wdzdeng.it.friend.common;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author wdz
 * @version 1.0
 * @date 2019/12/29 17:08
 */
@Data
@Accessors(chain = true)
public class DealResult<T> implements Serializable {
    private Boolean isSuccess;
    private T data;
    private DealResult(Boolean isSuccess, T data){
        this.isSuccess = isSuccess;
        this.data = data;
    }

    public static <T> DealResult<T> ok(T data){
        return new DealResult<>(Boolean.TRUE, data);
    }

    public static <T> DealResult<T> fail(T data){
        return new DealResult<>(Boolean.FALSE, data);
    }
}
