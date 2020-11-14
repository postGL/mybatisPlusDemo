package com.zbs.mybatisplus.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * description: SexEnum
 * date: 2020/11/12 18:39
 * author: zhangbs
 * version: 1.0
 */
public enum SexEnum {

    /**
     * 女
     */
    WOMAN(0, "女"),
    /**
     * 男
     */
    MAN(1, "男");

    @EnumValue
    @JsonValue    //标记响应json值
    private int sex;

    private String description;

    SexEnum(int sex, String description) {
        this.sex = sex;
        this.description = description;
    }

    public int getSex() {
        return sex;
    }

    public String getDescription() {
        return description;
    }

}
