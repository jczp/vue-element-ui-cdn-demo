package com.jczp.areaserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jczp.areaserver.common.R;
import com.jczp.areaserver.entity.FlightCrew;
import com.jczp.areaserver.service.FlightCrewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName FlightCrewController
 * @Description TODO
 * @Author Jared Cheung
 * @Date 2022/7/12 15:45
 * @Version 1.0
 */

@Api(tags = "area")
@CrossOrigin
@RestController
@RequestMapping("/FlightCrew")
public class FlightCrewController {

    @Autowired
    private FlightCrewService flightCrewService;

    @ApiOperation("查询列表无分页")
    @GetMapping("")
    public List<FlightCrew> selectList() {
        List<FlightCrew> list = flightCrewService.list();
        return list;
    }

    @ApiOperation("查询带条件列表分页")
    @GetMapping("/{current}/{size}")
    public R selectListByPage(@PathVariable Integer current, @PathVariable Integer size) {
        Page<FlightCrew> page = new Page<>(current, size); //从第current页开始，每页size条
        IPage<FlightCrew> iPage = flightCrewService.page(page);
        return R.ok().data("iPage",iPage);
    }

    @ApiOperation("查询带条件列表分页")
    @GetMapping("/{current}/{size}/{areaCode}")
    public R selectListByPageandAreaCode(@PathVariable Integer current, @PathVariable Integer size, @PathVariable Long areaCode) {
        Page<FlightCrew> page = new Page<>(current, size); //从第current页开始，每页size条
        System.out.println("areaCode = " + areaCode);
        IPage<FlightCrew> iPage = flightCrewService.page(page,new QueryWrapper<FlightCrew>().eq("area_code",areaCode));
        return R.ok().data("iPage",iPage);
    }
    @ApiOperation("查询单个信息，用于修改回显")
    @GetMapping("/{id}")
    public FlightCrew selectById(@PathVariable Long id) {
        return flightCrewService.getById(id);
    }

    @ApiOperation("删除单个机组信息")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        flightCrewService.removeById(id);
    }

    @ApiOperation("批量删除机组信息")
    @PostMapping("/deleteByIds")
    public void deleteByIds(@RequestBody Long[] ids) {
        flightCrewService.removeByIds(Arrays.asList(ids));
    }

    @ApiOperation("添加机组信息")
    @PostMapping("")
    public void insert(@RequestBody FlightCrew flightCrew) {
        flightCrewService.save(flightCrew);
    }

    @ApiOperation("修改机组信息")
    @PutMapping("")
    public void update(@RequestBody FlightCrew flightCrew) {
        flightCrewService.updateById(flightCrew);
    }
}
