package com.jc.cloud.vo.home;


import java.io.Serializable;
import java.util.Date;

public class LeaveVo implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 图片
     */
    private String leaveImg;

    /**
     * 宝宝
     */
    private String babyId;

    /**
     * 老师
     */
    private String teacherId;

    /**
     * 请假开始时间
     */
    private Date startTime;

    /**
     * 请假结束时间
     */
    private Date endTime;

    /**
     * 0待审批1审批-1驳回
     */
    private Integer status;

    /**
     * 审核人
     */
    private String checkBy;

    /**
     * 审核时间
     */
    private Date checkTime;

    private Integer version;

    private Date createTime;
}