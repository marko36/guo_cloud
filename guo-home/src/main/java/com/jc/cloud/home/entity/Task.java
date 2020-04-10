package com.jc.cloud.home.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 任务
 * </p>
 *
 * @author lq
 * @since 2019-06-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sch_task")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空")
    private String title;

    /**
     * 内容
     */
    @NotBlank(message = "内容不能为空")
    private String content;

    /**
     * 教师
     */
    @TableField("teacherId")
    private String teacherId;

    /**
     * 用户id
     */
    private String uid;

    /**
     * 任务标签
     */
    @TableField("taskLabel")
    private String taskLabel;

    /**
     * 任务类型
     */
    @TableField("taskType")
    private Integer taskType;

    /**
     * 任务开始时间
     */
    @TableField("startTime")
    private LocalDateTime startTime;

    /**
     * 任务结束时间
     */
    @TableField("endTime")
    private LocalDateTime endTime;

    /**
     * 目标
     */
    @TableField("targetId")
    private Integer targetId;

    /**
     * 领取数量
     */
    @TableField("receiveNumber")
    private Integer receiveNumber;

    /**
     * 1进行中2已结束
     */
    private Integer status;

    /**
     * 领取类型（默认，手动）
     */
    @TableField("receiveType")
    private String receiveType;

    /**
     * 完成数量
     */
    @TableField("completeNumber")
    private Integer completeNumber;

    /**
     * 任务图片
     */
    @TableField("taskLogo")
    private String taskLogo;

    /**
     * 领取人员
     */
    @TableField("receivePeople")
    private String receivePeople;

    @ApiParam(hidden = true)
    private Integer version;

    @TableField("delFlag")
    @ApiParam(hidden = true)
    private Integer delFlag;

    @TableField("createTime")
    @ApiParam(hidden = true)
    private LocalDateTime createTime;

    @TableField("updateTime")
    @ApiParam(hidden = true)
    private LocalDateTime updateTime;

    @TableField("createBy")
    @ApiParam(hidden = true)
    private String createBy;

    @TableField("updateBy")
    @ApiParam(hidden = true)
    private String updateBy;


}
