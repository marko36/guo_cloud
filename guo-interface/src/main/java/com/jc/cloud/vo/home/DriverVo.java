package com.jc.cloud.vo.home;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

public class DriverVo {

    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    private String name;

    private Integer age;

    private String phone;

    private String photo;

    /**
     * 位置
     */
    private String position;

    private String schoolBusId;

    private Integer version;

    private Boolean delFlag;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String createBy;

    private String updateBy;

}
