package com.jc.cloud.vo.home;

import java.io.Serializable;
import java.util.Date;

public class TaskVo implements Serializable {
    /**
     * id
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    private String uid;

    /**
     * 教师
     */
    private String teacherId;

    /**
     * 任务标签
     */
    private String taskLabel;

    /**
     * 任务类型
     */
    private Integer taskType;

    /**
     * 任务开始时间
     */
    private Date startTime;

    /**
     * 任务结束时间
     */
    private Date endTime;

    /**
     * 目标
     */
    private Integer targetId;

    /**
     * 领取数量
     */
    private Integer receiveNumber;

    /**
     * 0待审核1进行中2已结束
     */
    private Integer status;

    /**
     * 领取类型（默认，手动）
     */
    private String receiveType;

    /**
     * 完成数量
     */
    private Integer completeNumber;

    /**
     * 任务图片
     */
    private String taskLogo;

    /**
     * 领取人员
     */
    private String receivePeople;

    private Integer version;

    private Integer delFlag;

    private Date createTime;

    private Date updateTime;

    private String createBy;

    private String updateBy;
}
