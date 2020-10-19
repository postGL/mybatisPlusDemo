package com.zbs.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zbs.mybatisplus.dao.entity.User;
import com.zbs.mybatisplus.dao.mapper.UserMapper;
import com.zbs.mybatisplus.qo.UserQO;
import com.zbs.mybatisplus.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhangbs
 * @since 2020-10-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 分页查询列表
     *
     * @param userQO
     * @return 列表
     */
    @Override
    public IPage<User> selectPageList(Page<User> page, UserQO userQO) {
        // 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题，这时候你需要自己查询 count 部分
        // page.setOptimizeCountSql(false);
        // 当 total 为小于 0 或者设置 setSearchCount(false) 分页插件不会进行 count 查询
        // 要点!! 分页返回的对象与传入的对象是同一个
//        userMapper.selectPage()
        return userMapper.selectPageList(page, userQO);
    }

}
