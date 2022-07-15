package com.jczp.areaserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jczp.areaserver.entity.Area;
import com.jczp.areaserver.entity.FlightCrew;
import com.jczp.areaserver.mapper.AreaMapper;
import com.jczp.areaserver.mapper.FlightCrewMapper;
import com.jczp.areaserver.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jared
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements AreaService {

    @Autowired
    private FlightCrewMapper flightCrewMapper;

    @Autowired AreaMapper areaMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByIdAndFlightCrew(Long id) {
        Area area = areaMapper.selectById(id);
        String areaCode = area.getAreaCode();
        flightCrewMapper.delete(new QueryWrapper<FlightCrew>().eq("area_code",areaCode));
        areaMapper.deleteById(id);
    }
}
