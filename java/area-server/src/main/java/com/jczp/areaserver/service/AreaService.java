package com.jczp.areaserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jczp.areaserver.entity.Area;


public interface AreaService extends IService<Area> {

    void removeByIdAndFlightCrew(Long id);
}
