package com.wdzdeng.it.friend.service;

import com.wdzdeng.it.friend.entity.UserResult;
import com.wdzdeng.it.friend.vo.RankVO;

import java.util.List;
import java.util.TreeSet;

/**
 * @author wdz
 * @version 1.0
 * @date 2019/12/30 22:13
 */
public interface IMatchService {
     String matchFirst(List<UserResult> userResults, String answer);
     String compare(TreeSet<RankVO> rankVoSet, String currentAnswer, List<UserResult> userResults);
}
