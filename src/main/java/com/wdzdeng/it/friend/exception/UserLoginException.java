package com.wdzdeng.it.friend.exception;

/**
 * @author wdz
 * @version 1.0
 * @date 2019/12/29 15:15
 */
public class UserLoginException extends Exception {
    public UserLoginException(){
        super("用户登陆失败");
    }
}
