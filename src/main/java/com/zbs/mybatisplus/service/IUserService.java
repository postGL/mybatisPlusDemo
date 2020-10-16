package com.zbs.mybatisplus.service;

import com.zbs.mybatisplus.dao.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangbs
 * @since 2020-10-16
 */
public interface IUserService extends IService<User> {

    /**
     * 分页查询列表
     *
     * @param user 
     * @return 列表
     */
    List<User> selectPageList(User user);
}
