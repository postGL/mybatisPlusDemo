package com.zbs.mybatisplus.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * </p>
 *
 * @author zhangbs
 * @since 2020-10-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
//这里的value必须时数据库字段名，否则INSERT INTO user表 ( id, 姓名, age, email, sex ) VALUES ( ?, ?, ?, ?, ? )
@ApiModel(value = "User对象")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "姓名")
    @TableField(value = "name")
    //这里的value必须时数据库字段名，否则INSERT INTO user表 ( id, 姓名, age, email, sex ) VALUES ( ?, ?, ?, ?, ? )
    private String name;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "是否删除,1：删除；0：未删除")
    @TableLogic(value = "1", delval = "0")
    private Integer isDelete;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
