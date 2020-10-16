package com.zbs.mybatisplus.dao.mapper;

import com.zbs.mybatisplus.dao.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangbs
 * @since 2020-10-16
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 分页查询列表
     *
     * @param user 
     * @return 列表
     */
    List<User> selectPageList(User user);

}
