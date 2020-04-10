package com.jc.cloud.vo.home;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayVo implements Serializable {
    private Integer id;

    private String name;

    /**
     * 时长
     */
    private String duration;

    private String logo;

    /**
     * 首次付费价格
     */
    private BigDecimal firstPrice;

    /**
     * 第二次付费价格
     */
    private BigDecimal secondPrice;

    private Integer version;

    private Boolean delFlag;

    private Date createTime;

    private Date updateTime;

    private String createBy;

    private String updateBy;

}
