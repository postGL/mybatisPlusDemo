package com.zbs.mybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbs.mybatisplus.beans.qo.UserQO;
import com.zbs.mybatisplus.common.AjaxResult;
import com.zbs.mybatisplus.dao.entity.User;
import com.zbs.mybatisplus.dao.mapper.UserMapper;
import com.zbs.mybatisplus.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * controller
 * </p>
 *
 * @author zhangbs
 * @since 2020-10-16
 */
@Api(tags = "用户管理相关接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value = "分页查询列表")
    @GetMapping("/list")
    public AjaxResult list(Page<User> page, UserQO userQO) {
        return AjaxResult.success(userService.selectPageList(page, userQO));
    }

    @ApiOperation(value = "测试and和or")
    @PostMapping(value = "/andOr")
    public void testAndOr(@RequestBody @Valid User user) {
        /**
         * SELECT
         *  a.*
         * FROM
         * 	user a
         * WHERE
         * 	a.sex == 1
         * 	AND ( a.`name` = 'jack' OR a.age = '20' )
         */
        LambdaQueryWrapper<User> userQueryWrapper = new LambdaQueryWrapper<>();
        userQueryWrapper.eq(User::getSex, user.getSex());
        userQueryWrapper.and(i -> i
                .eq(User::getName, user.getName())
                .or()
                .eq(User::getAge, user.getAge())
        );

    }

    @ApiOperation(value = "获取详情")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        /**
         * SELECT * FROM user WHERE id = 20
         */

        // 方式一：QueryWrapper
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        userMapper.selectList(queryWrapper);

        // 方式二：QueryWrapper + lambda
        QueryWrapper<User> queryWrapper2 = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getId, id);
        userMapper.selectList(queryWrapper2);

        // 方式三：查询可以使用lambdaQuery()的方式查询
        User user = userService.lambdaQuery().eq(User::getId, id).one();

        return AjaxResult.success(userService.getById(id));
    }

    @ApiOperation(value = "新增")
    @PostMapping
    public AjaxResult add(@RequestBody @Valid User user) {
        userService.save(user);
        return AjaxResult.success();
    }

    @ApiOperation(value = "修改")
    @PutMapping
    public AjaxResult edit(@RequestBody @Valid User user) {
        userService.updateById(user);
        return AjaxResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable List<Long> ids) {
        userService.removeByIds(ids);
        return AjaxResult.success();
    }

}

