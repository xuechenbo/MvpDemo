package com.kahuanbao.com.model;

import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2019/4/12.
 *
 */

public class RecyclerBean implements Comparable<RecyclerBean>{
    private String name;
    private String firstLetter;
    private String pinyin;

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getFirstLetter() {
        return firstLetter;
    }
    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(@NonNull RecyclerBean another) {
        if (firstLetter.equals("#") && !another.firstLetter.equals("#")) {
            return 1;
        } else if (!firstLetter.equals("#") && another.firstLetter.equals("#")){
            return -1;
        } else {
            return pinyin.compareToIgnoreCase(another.pinyin);
        }
    }
}
