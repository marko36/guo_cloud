package com.jc.cloud.vo.home;


import java.io.Serializable;
import java.util.Date;

public class RideVo implements Serializable {
    private String id;

    private String babyId;

    private String schoolBus;

    /**
     * 上车时间
     */
    private Date startTime;

    private Integer version;

    private Date createTime;
}
