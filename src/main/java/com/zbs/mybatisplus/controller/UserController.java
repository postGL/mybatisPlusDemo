package com.zbs.mybatisplus.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbs.mybatisplus.beans.qo.UserQO;
import com.zbs.mybatisplus.common.AjaxResult;
import com.zbs.mybatisplus.dao.entity.User;
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

    @ApiOperation(value = "分页查询列表")
    @GetMapping("/list")
    public AjaxResult list(Page<User> page, UserQO userQO) {
        return AjaxResult.success(userService.selectPageList(page, userQO));
    }

    @ApiOperation(value = "获取详情")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        // 查询可以使用lambdaQuery()的方式查询
//        User user = userService.lambdaQuery().eq(User::getId, id).one();
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

