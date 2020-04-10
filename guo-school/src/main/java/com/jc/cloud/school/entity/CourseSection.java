package com.jc.cloud.school.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * <p>
 * 课程章节
 * </p>
 *
 * @author chenjian
 * @since 2019-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sxy_course_section")
public class CourseSection implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 章节名
     */
    @NotBlank(message = "章节名不能为空")
    private String name;

    /**
     * 课程编号
     */
    @TableField("courseId")
    @NotNull(message = "关联课程编号不能为空")
    private Integer courseId;

    /**
     * 视频地址
     */
    @TableField("videoUrl")
    @NotBlank(message = "视频地址不能为空")
    private String videoUrl;

    /**
     * 排序
     */
    @NotNull(message = "排序编号不能为空")
    private Integer sequence;

    /**
     * 播放时间长度
     */
    @JSONField(format = "HH:mm:ss")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime time;

    /**
     * 0待审核 1正常 -1审核失败 
     */
    @ApiModelProperty(hidden = true)
    private Integer status;

    /**
     * 是否删除
     */
    @TableField("delFlag")
    @ApiModelProperty(hidden = true)
    private Boolean delFlag;

    /**
     * 版本号
     */
    @JSONField(serialize = false)
    @ApiModelProperty(hidden = true)
    private Integer version;

    @TableField("createTime")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss",serialize = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(hidden = true)
    private LocalDateTime createTime;

    @TableField("createBy")
    @JSONField(serialize = false)
    @ApiModelProperty(hidden = true)
    private String createBy;

    @TableField("updateTime")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss",serialize = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(hidden = true)
    private LocalDateTime updateTime;

    @TableField("updateBy")
    @JSONField(serialize = false)
    @ApiModelProperty(hidden = true)
    private String updateBy;
}
