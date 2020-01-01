package com.wdzdeng.it.friend.exception;

/**
 * @author wdz
 * @version 1.0
 * @date 2019/12/29 14:41
 */
public class CodeEmptyException extends Exception {
    public CodeEmptyException(){
        super("code is null");
    }

}
