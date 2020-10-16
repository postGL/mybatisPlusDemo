package com.zbs.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zbs.mybatisplus.dao.entity.User;
import org.springframework.stereotype.Repository;

/**
 * description: UserMapper
 * date: 2020/10/15 18:29
 * author: zhangbs
 * version: 1.0
 */
/**
 * 配置了@MapperScan("com.zbs.mybatisplus.dao")包扫描和mapper上注解@Repository都会在spring中
 * 注册bean，作用一样，
 * 不一样的地方就是，前者在注入UserMapper时报红线找不到，但是可以用（编译阶段找不到，运行可以）
 * 后者没有此问题
 * */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
