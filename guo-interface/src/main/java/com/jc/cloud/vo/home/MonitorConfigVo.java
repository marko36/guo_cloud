package com.jc.cloud.vo.home;


import java.io.Serializable;
import java.time.LocalDateTime;

public class MonitorConfigVo implements Serializable {
    /**
     * id
     */
    private String id;

    /**
     * 设备地址
     */
    private String addrs;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 类型
     */
    private String model;

    /**
     * 学校Id
     */
    private String school;

    /**
     * 是否启用
     */
    private Boolean isEnable;

    /**
     * 设备登录用户名
     */
    private String userName;

    /**
     * 设备登录密码
     */
    private String password;

    /**
     * 安装时间
     */
    private LocalDateTime erectionTime;

    /**
     * 安装员
     */
    private String erectionBy;
}
