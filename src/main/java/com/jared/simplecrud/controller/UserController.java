package com.jared.simplecrud.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jared.simplecrud.common.R;
import com.jared.simplecrud.entity.User;
import com.jared.simplecrud.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Api(tags = "CRUD")
@CrossOrigin
@RestController
@RequestMapping("/CRUD")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("查询列表无分页")
    @GetMapping("")
    public List<User> selectList() {
        List<User> list = userService.list();
        return list;
    }

    @ApiOperation("查询带条件列表分页")
    @GetMapping("/{current}/{size}")
    public R selectListByPage(@PathVariable Integer current, @PathVariable Integer size) {
        Page<User> page = new Page<User>(current, size); //从第current页开始，每页size条
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        //带有查询条件//使用lambda表达式实现多次like查询
//        queryWrapper.and(wq -> wq.like().or().like().or().like());
//        IPage<User> iPage = userService.page(page, queryWrapper);
        IPage<User> iPage = userService.page(page);
        return R.ok().data("iPage",iPage);
    }

    @ApiOperation("查询单个信息，用于修改回显")
    @GetMapping("/{id}")
    public User selectById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @ApiOperation("删除单个用户")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.removeById(id);
    }

    @ApiOperation("批量删除用户")
    @PostMapping("/deleteByIds")
    public void deleteByIds(@RequestBody Long[] ids) {
        userService.removeByIds(Arrays.asList(ids));
    }

    @ApiOperation("添加用户")
    @PostMapping("")
    public void insert(@RequestBody User user) {
        userService.save(user);
    }

    @ApiOperation("更新修改信息")
    @PutMapping("")
    public void update(@RequestBody User user) {
        userService.updateById(user);
    }
}

