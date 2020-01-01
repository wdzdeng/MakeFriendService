package com.wdzdeng.it.friend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wdzdeng.it.friend.entity.UserResult;
import com.wdzdeng.it.friend.exception.SaveAnswerException;

/**
 * @author wdz
 * @version 1.0
 * @date 2019/12/28 23:05
 */
public interface IUserResultService extends IService<UserResult> {
    void save(Integer userId, String answer) throws SaveAnswerException;
    void timeTaskForUpdateMatch();
}
