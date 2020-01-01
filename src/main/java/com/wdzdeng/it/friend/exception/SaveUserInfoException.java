package com.wdzdeng.it.friend.exception;

/**
 * @author wdz
 * @version 1.0
 * @date 2019/12/29 21:03
 */
public class SaveUserInfoException extends Exception {
    public SaveUserInfoException(){
        super("个人信息保存失败");
    }
}
