package com.wdzdeng.it.friend.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @author wdz
 * @version 1.0
 * @date 2019/12/30 21:15
 */
@Data
@Accessors(chain = true)
public class RankVO implements Comparable<RankVO>,Serializable {
    private Integer userId;
    private String name;
    private Short gender;
    private String headUrl;
    private String answerCompare;
    private Integer match;

    @Override
    public int compareTo(RankVO o) {
         return o.match - match;
    }
}
