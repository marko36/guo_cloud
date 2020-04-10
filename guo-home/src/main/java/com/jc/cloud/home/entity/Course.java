package com.jc.cloud.home.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author lq
 * @since 2019-06-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sch_course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 星期
     */
    private LocalDate week;

    /**
     * 上午
     */
    private String morning;

    /**
     * 下午
     */
    private String afternoon;

    /**
     * 教师
     */
    @TableField("teacherId")
    private String teacherId;

    /**
     * 学校
     */
    @TableField("schoolId")
    private String schoolId;

    /**
     * 课程图片
     */
    private String pictures;

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
