package com.jc.cloud.vo.home;


import java.io.Serializable;
import java.util.Date;

public class RecipesVo implements Serializable {
    /**
     * id
     */
    private String id;

    /**
     * 食谱名
     */
    private String name;

    /**
     * 早餐
     */
    private String breakfast;

    /**
     * 午餐
     */
    private String lunch;

    /**
     * 晚餐
     */
    private String dinner;

    /**
     * 星期
     */
    private Date week;

    /**
     * 学校
     */
    private String schoolId;

    private Integer version;

    private Boolean delFlag;

    private Date createTime;

    private Date updateTime;

    private String createBy;

    private String updateBy;
}
