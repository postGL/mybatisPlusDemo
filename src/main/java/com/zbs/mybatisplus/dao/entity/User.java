package com.zbs.mybatisplus.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zbs.mybatisplus.common.enums.SexEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
    @TableId(value = "id", type = IdType.AUTO)
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
    private SexEnum sex;

    /**
     * tinyint一个字节   smallint  两个字节   MEDIUMINT三个字节   int四个字节
     * 不管 tinyint 后面的数字是多少，它存储长度=2^（1字节）=2^8，即存储范围是 -2^7 到 2^7 - 1 -128~127
     *
     * tinyint(1) 和 tinyint(4) 中的1和4并不表示存储长度，只有字段指定zerofill是有用，
     * 如tinyint(4)，如果实际值是2，如果列指定了zerofill，查询结果就是0002，左边用0来填充。
     */
    @ApiModelProperty(value = "是否成年")
    private Boolean isAdult;

    @ApiModelProperty(value = "是否删除,1：删除；0：未删除")
    @TableLogic(value = "1", delval = "0")
    private Integer isDelete;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
