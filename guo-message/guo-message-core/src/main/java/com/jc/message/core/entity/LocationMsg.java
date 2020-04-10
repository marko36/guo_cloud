package com.jc.message.core.entity;

import lombok.Data;

import java.io.Serializable;
/**
 *  车辆地理位置更新
* */
@Data
public class LocationMsg implements Serializable {
    /**
     *车辆id
     * */
    private String carId;
    /**
     *经度
    * */
    private String longitude;
    /**
     * 纬度
     * */
    private String latitude;

    private Long time;

    public LocationMsg(){}

    public LocationMsg(String carId,String longitude,String latitude){
        this.carId = carId;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
