package com.zbs.mybatisplus.controller;

import com.zbs.mybatisplus.common.AjaxResult;
import com.zbs.mybatisplus.dao.entity.User;
import com.zbs.mybatisplus.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  controller
 * </p>
 *
 * @author zhangbs
 * @since 2020-10-16
 */
@Api(tags = "")
@RestController
@RequestMapping("/user")
public class UserController{

    @Resource
    private IUserService userService;

//    @ApiOperation(value = "分页查询列表")
//    @GetMapping("/list")
//    public PageInfo list(User user) {
//        startPage();
//        List<User> list = userService.selectPageList(user);
//        return getDataTable(list);
//    }

    @ApiOperation(value = "获取详情")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
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

