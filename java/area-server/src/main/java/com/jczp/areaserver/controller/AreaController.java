package com.jczp.areaserver.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jczp.areaserver.common.R;
import com.jczp.areaserver.entity.Area;
import com.jczp.areaserver.service.AreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Api(tags = "area")
@CrossOrigin
@RestController
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @ApiOperation("查询列表无分页")
    @GetMapping("")
    public List<Area> selectList() {
        List<Area> list = areaService.list();
        return list;
    }

    @ApiOperation("查询带条件列表分页")
    @GetMapping("/{current}/{size}")
    public R selectListByPage(@PathVariable Integer current, @PathVariable Integer size) {
        Page<Area> page = new Page<>(current, size); //从第current页开始，每页size条
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        //带有查询条件//使用lambda表达式实现多次like查询
//        queryWrapper.and(wq -> wq.like().or().like().or().like());
//        IPage<User> iPage = userService.page(page, queryWrapper);
        IPage<Area> iPage = areaService.page(page);
        return R.ok().data("iPage",iPage);
    }

    @ApiOperation("查询单个信息，用于修改回显")
    @GetMapping("/{id}")
    public Area selectById(@PathVariable Long id) {

        return areaService.getById(id);
    }

    //关联删除区域机组信息
    @ApiOperation("删除单个区域信息")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        areaService.removeByIdAndFlightCrew(id);
    }

    //没添加关联删除看看就行了不用他
    @ApiOperation("批量删除区域信息")
    @PostMapping("/deleteByIds")
    public void deleteByIds(@RequestBody Long[] ids) {
        areaService.removeByIds(Arrays.asList(ids));
    }

    @ApiOperation("添加区域信息")
    @PostMapping("")
    public void insert(@RequestBody Area area) {
        areaService.save(area);
    }

    @ApiOperation("修改区域信息")
    @PutMapping("")
    public void update(@RequestBody Area area) {
        areaService.updateById(area);
    }

}

