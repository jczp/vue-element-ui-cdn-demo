package com.jczp.areaserver.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName FlightCrew
 * @Description TODO
 * @Author Jared Cheung
 * @Date 2022/7/12 14:42
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "flight_crew")
public class FlightCrew implements Serializable {
        @JsonSerialize(using = ToStringSerializer.class)
        @TableId(type = IdType.AUTO)
        private Long id;
        @TableField(value = "flight_crew_code")
        private String flightCrewCode;
        @TableField(value = "flight_crew_name")
        private String flightCrewName;
        @TableField(value = "flight_crew_handname")
        private String flightCrewHandName;
        @TableField(value = "area_code")
        private String areaCode;
        @TableField(value = "create_time",fill = FieldFill.INSERT)
        private Date createTime;
        @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
        private Date updateTime;
        @TableLogic
        @TableField(fill = FieldFill.INSERT)
        private Integer deleted;
    }