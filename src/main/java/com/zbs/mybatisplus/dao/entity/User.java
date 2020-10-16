package com.zbs.mybatisplus.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * description: User
 * date: 2020/10/15 18:28
 * author: zhangbs
 * version: 1.0
 */
@Data
@TableName(value = "user")
public class User {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField(value = "姓名")
    private String name;

    @TableField(value = "年龄")
    private Integer age;

    @TableField(value = "邮箱")
    private String email;
}
