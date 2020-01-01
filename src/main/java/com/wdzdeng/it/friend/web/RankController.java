package com.wdzdeng.it.friend.web;

import com.wdzdeng.it.friend.common.R;
import com.wdzdeng.it.friend.entity.UserResult;
import com.wdzdeng.it.friend.service.IUserResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author wdz
 * @version 1.0
 * @date 2019/12/31 13:22
 */
@RestController
@RequestMapping(value = "/rank", produces = "application/json;charset=UTF-8")
public class RankController {
    @Autowired
    IUserResultService resultService;
    @GetMapping("/list")
    public R<String> rank(@RequestParam("userId") Integer userId){
        if (null == userId){
            return R.failed("用户id不合法");
        }
        UserResult userResult = resultService.getById(userId);
        if (null == userResult){
            return R.failed("先完成答题部分");
        }
        return R.ok(userResult.getMatchSet());
    }
}
