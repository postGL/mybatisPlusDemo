package com.zbs.mybatisplus.dao.entity;

import lombok.Data;

/**
 * description: User
 * date: 2020/10/15 18:28
 * author: zhangbs
 * version: 1.0
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
