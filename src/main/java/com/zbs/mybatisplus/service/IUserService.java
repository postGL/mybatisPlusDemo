package com.zbs.mybatisplus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zbs.mybatisplus.beans.qo.UserQO;
import com.zbs.mybatisplus.dao.entity.User;

/**
 * <p>
 * 服务类
 * </p>
 * @author zhangbs
 * @since 2020-10-16
 */
public interface IUserService extends IService<User> {

    /**
     * 分页查询列表
     * @param userQO
     * @return 列表
     */
    IPage<User> selectPageList(Page page, UserQO userQO);
}
