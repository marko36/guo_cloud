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
 * 标签表
 * </p>
 *
 * @author liuqing
 * @since 2019-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ryj_label")
public class Label implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * 老师
     */
    @TableField("teacherId")
    @NotBlank(message = "老师不能为空")
    private String teacherId;

    /**
     * 家长
     */
    @TableField("parentId")
    private String parentId;

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
