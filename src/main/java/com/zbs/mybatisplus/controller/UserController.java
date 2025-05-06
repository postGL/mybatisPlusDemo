package com.zbs.mybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbs.mybatisplus.beans.qo.UserQO;
import com.zbs.mybatisplus.common.AjaxResult;
import com.zbs.mybatisplus.common.enums.SexEnum;
import com.zbs.mybatisplus.dao.entity.User;
import com.zbs.mybatisplus.dao.mapper.UserMapper;
import com.zbs.mybatisplus.service.UserService;
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
 * @author zhangbs
 * @since 2020-10-16
 */
@Api(tags = "用户管理相关接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value = "分页查询列表")
    @GetMapping("/list")
    public AjaxResult list(int pageSize, int pageNum, UserQO userQO) {
        return AjaxResult.success(userService.selectPageList(new Page<>(pageNum, pageSize), userQO));
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
        // 如果没有上一句，会自动去除and
        userQueryWrapper.and(i -> i
                .eq(User::getName, user.getName())
                .or()
                .eq(User::getAge, user.getAge())
        );
        List<User> list = userMapper.selectList(userQueryWrapper);

        /**
         * SELECT
         * 	*
         * FROM
         * USER
         * WHERE
         * 	( NAME = ? OR age = ? )
         */
        LambdaQueryWrapper<User> userQueryWrapper1 = new LambdaQueryWrapper<>();
        userQueryWrapper1.eq(User::getName, user.getName())
                .or()
                .eq(User::getAge, user.getAge());
        List<User> list1 = userMapper.selectList(userQueryWrapper1);

        /**
         * SELECT
         * 	a.*
         * FROM
         * 	user_info a
         * WHERE
         * 	a.sex <> 1
         * 	AND ( (a.`name` = 'jack' AND a.age = '20') OR (a.email = '13888888888' OR a.age = '2') )
         */
        LambdaQueryWrapper<User> userQueryWrapper2 = new LambdaQueryWrapper<>();
        userQueryWrapper2.ne(User::getSex, user.getSex());
        userQueryWrapper2.and(i ->
                (i.and(j -> j.eq(User::getName, user.getName()).eq(User::getAge, user.getAge())))
                        .or(j -> j.eq(User::getEmail, user.getEmail()).or().eq(User::getAge, user.getAge()))
        );
        List<User> list2 = userMapper.selectList(userQueryWrapper2);
    }

    @ApiOperation(value = "测试getOne")
    @GetMapping(value = "/getOne")
    public void testGetOne() {
        // 除了有getById进行主键查询。还有getOne方法
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getName, "张大侠");
        // false：据 Wrapper，查询一条记录；有多个 result 是否抛出异常
        // true(默认值)：默认取第一条数据返回，所以当我们查询为多条数据时，默认返回第一条
	    User one = userService.getOne(lambdaQueryWrapper, false);
	    System.out.println(one);

	    List<User> userIdList = userService.list(new QueryWrapper<User>().lambda().eq(User::getName, "张大侠").select(User::getId));
	    System.out.println(userIdList);
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
    @PostMapping(value = "/add")
    public AjaxResult add(@RequestBody @Valid User user) {
        userService.save(user);
        // 即使user对象不传id值，在save保存值后，user对象的id会被赋予新值返回
        System.out.println(user);
        return AjaxResult.success();
    }

    @ApiOperation(value = "测试通用枚举")
    @PostMapping(value = "/addUserEnum")
    public AjaxResult addUserEnum() {
        User user = new User().setAge(18).setName("zhangsan").setEmail("250@qq.com").setSex(SexEnum.MAN);
        userService.save(user);
        return AjaxResult.success();
    }

    @ApiOperation(value = "修改")
    @PutMapping(value = "/edit")
    public AjaxResult edit(@RequestBody @Valid User user) {
        userService.updateById(user);
        return AjaxResult.success();
    }

    @ApiOperation(value = "条件保存并更新")
    @PutMapping(value = "/saveOrUpdate")
    public AjaxResult saveOrUpdate(@RequestBody User user) {
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getAge, user.getAge())
                .eq(User::getName, user.getName());
        userService.saveOrUpdate(user, wrapper);
        return AjaxResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable List<Long> ids) {
        userService.removeByIds(ids);
        return AjaxResult.success();
    }

}

