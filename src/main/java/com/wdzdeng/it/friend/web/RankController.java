package com.wdzdeng.it.friend.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wdzdeng.it.friend.common.R;
import com.wdzdeng.it.friend.entity.UserResult;
import com.wdzdeng.it.friend.service.IUserResultService;
import com.wdzdeng.it.friend.vo.RankVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.TreeSet;

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
    public R<TreeSet<RankVO>> rank(@RequestParam("userId") Integer userId){
        if (null == userId){
            return R.failed("用户id不合法");
        }
        UserResult userResult = resultService.getById(userId);
        if (null == userResult){
            return R.failed("先完成答题部分");
        }
        TreeSet<RankVO> rankVoTreeSet = JSON.parseObject(userResult.getMatchSet(), new TypeReference<TreeSet<RankVO>>(){});
        return R.ok(rankVoTreeSet);
    }
}
