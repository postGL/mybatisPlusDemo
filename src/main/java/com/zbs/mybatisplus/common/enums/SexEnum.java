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

    // 如果数据库的数值，在枚举中找不到对应的值，则会导致不返回该字段
    @EnumValue     //在需要保存到数据库的值上面加上注解
    @JsonValue    //标记响应json值，需要在前端展示哪个值就在哪个属性上加上该注解
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
