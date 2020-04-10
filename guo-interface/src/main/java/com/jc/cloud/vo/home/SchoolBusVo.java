package com.jc.cloud.vo.home;


import java.io.Serializable;
import java.util.Date;

public class SchoolBusVo implements Serializable {
    private String id;

    private String name;

    private String schoolId;

    /**
     * 路线
     */
    private Integer routeId;

    /**
     * 纬度
     */
    private String lat;

    /**
     * 经度
     */
    private String lon;

    /**
     * 当前工作Id
     */
    private String currentWorkId;

    /**
     * 最后一次工作id
     */
    private String lastWorkId;

    private Integer uid;

    private Integer workStatus;

    /**
     * 路线
     */
    private String route;

    /**
     * 座位数
     */
    private Integer seat;

    /**
     * 车牌号
     */
    private String license;

    private String address;

    private Integer version;

    private Date createTime;

    private String createBy;
}
