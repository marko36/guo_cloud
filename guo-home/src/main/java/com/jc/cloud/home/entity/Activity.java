package com.jc.cloud.home.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiParam;
import jdk.internal.instrumentation.TypeMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 活动
 * </p>
 *
 * @author lq
 * @since 2019-06-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sch_activity")
public class Activity implements Serializable {

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
     * 角色
     */
    private String uid;

    /**
     * 教师
     */
    @TableField("teacherId")
    @NotBlank(message = "教师不能为空")
    private String teacherId;

    /**
     * 学生
     */
    @TableField("babyId")
    private String babyId;

    /**
     * 班级
     */
    @TableField("classId")
    @NotBlank(message = "班级不能为空")
    private String classId;

    /**
     * logo
     */
    @TableField("activiLogo")
    private String activiLogo;

    /**
     * 0待审核1已发布
     */
    private Integer status;

    @ApiParam(hidden = true)
    private Integer version;

    @TableField("delFlag")
    @ApiParam(hidden = true)
    private Boolean delFlag;

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
