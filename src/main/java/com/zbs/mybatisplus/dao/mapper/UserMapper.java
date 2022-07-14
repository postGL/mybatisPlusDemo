package com.zbs.mybatisplus.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbs.mybatisplus.dao.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhangbs
 * @since 2020-10-16
 */
//@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 分页查询列表
     *
     * @param user
     * @return 列表
     */
    IPage<User> selectPageList(Page<?> page, @Param("u") User user);

}
