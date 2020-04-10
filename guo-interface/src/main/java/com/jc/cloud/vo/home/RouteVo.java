package com.jc.cloud.vo.home;

import com.baomidou.mybatisplus.annotation.TableField;

import java.time.LocalDateTime;

public class RouteVo {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    @TableField("startTime")
    private LocalDateTime startTime;

    @TableField("endTime")
    private LocalDateTime endTime;
}
