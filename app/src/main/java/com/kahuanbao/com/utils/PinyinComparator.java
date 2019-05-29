package com.kahuanbao.com.utils;

import com.kahuanbao.com.model.RecyclerBean;

import java.util.Comparator;

/**
 * Created by Administrator on 2019/4/12.
 *
 */

public class PinyinComparator implements Comparator<RecyclerBean> {

    public int compare(RecyclerBean o1, RecyclerBean o2) {
        if (o1.getFirstLetter().equals("@")
                || o2.getFirstLetter().equals("#")) {
            return -1;
        } else if (o1.getFirstLetter().equals("#")
                || o2.getFirstLetter().equals("@")) {
            return 1;
        } else {
            return o1.getFirstLetter().compareTo(o2.getFirstLetter());
        }
    }

}