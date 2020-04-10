package com.jc.cloud.vo.home;


import java.io.Serializable;
import java.util.Date;

public class MonitorVo implements Serializable {
    /**
     *
     */
    private String id;

    /**
     * 监控器显示名称
     */
    private String displayName;

    /**
     * 监控器名称
     */
    private String monitorName;

    /**
     * 1.公共区域 2.班级区域
     */
    private Integer region;

    /**
     * 播放人数
     */
    private Integer playCount;

    /**
     * 学校Id
     */
    private String school;

    /**
     * 1.正常  2.报修 3.维修
     */
    private Integer status;

    /**
     * 监控封面每隔一分钟动态获取
     */
    private String cover;

    private Date createTime;

    private String createBy;
}
