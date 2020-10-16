package com.zbs.mybatisplus.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 
* </p>
*
* @author zhangbs
* @since 2020-10-16
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
@ApiModel(value="User对象", description="")
public class User extends Model<User> {

  private static final long serialVersionUID=1L;

  @ApiModelProperty(value = "主键ID")
  private Long id;

  @ApiModelProperty(value = "姓名")
  private String name;

  @ApiModelProperty(value = "年龄")
  private Integer age;

  @ApiModelProperty(value = "邮箱")
  private String email;

  @ApiModelProperty(value = "性别")
  private Integer sex;


  @Override
  protected Serializable pkVal() {
    return this.id;
  }

}
