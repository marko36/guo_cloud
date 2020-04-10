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
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 监控设备
 * </p>
 *
 * @author lq
 * @since 2019-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_monitor")
public class Monitor implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *  id
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 监控器显示名称
     */
    @TableField("displayName")
    @NotBlank(message = "监控器显示名称不能为空")
    private String displayName;

    /**
     * 监控器名称
     */
    @TableField("monitorName")
    @NotBlank(message = "监控器名称不能为空")
    private String monitorName;

    /**
     * 班级
     */
    @TableField("classId")
    @NotBlank(message = "班级名称不能为空")
    private String classId;

    /**
     * 监控配置id
     */
    @TableField("monitorId")
    @NotBlank(message = "监控配置id不能为空")
    private String monitorId;

    /**
     * 1.公共区域 2.班级区域
     */
    @NotNull(message = "公共区域不能为空")
    private Integer region;

    /**
     * 播放人数
     */
    @TableField("playCount")
    @NotNull(message = "播放人数不能为空")
    private Integer playCount;

    /**
     * 学校Id
     */
    @NotNull(message = "学校不能为空")
    private String school;

    /**
     * 1.正常  2.报修 3.维修 
     */
    private Integer status;

    /**
     * 监控封面每隔一分钟动态获取
     */
    @NotNull(message = "监控封面每隔一分钟动态获取不能为空")
    private String cover;

    @TableField("createTime")
    @ApiParam(hidden = true)
    private LocalDateTime createTime;

    @TableField("createBy")
    @ApiParam(hidden = true)
    private String createBy;


}
