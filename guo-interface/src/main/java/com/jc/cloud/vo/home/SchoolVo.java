package com.jc.cloud.vo.home;


import java.io.Serializable;
import java.util.Date;

public class SchoolVo implements Serializable {
    /**
     * id
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 地址
     */
    private String address;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 描述
     */
    private String describe;

    private Boolean activity;

    /**
     * logo
     */
    private String schoolLogo;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 用户角色
     */
    private String uid;

    private Integer nature;

    /**
     * 经度
     */
    private String lon;

    /**
     * 纬度
     */
    private String lat;

    private Integer version;

    private Boolean delFlag;

    /**
     * 注册时间
     */
    private Date registerTime;

    private Date updateTime;

    private String createBy;

    private String updateBy;

}
