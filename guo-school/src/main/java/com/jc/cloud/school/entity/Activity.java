package com.jc.cloud.school.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 特训营
 * </p>
 *
 * @author chenjian
 * @since 2019-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sxy_activity")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 特训编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空")
    private String title;

    /**
     * 封面
     */
    //@NotBlank(message = "封面图片不能为空")
    private String cover;
    /**
     * 项目介绍图片地址
     */
    //@NotBlank(message = "项目介绍不能为空")
    private String presentation;

    /**
     * 讲师介绍图片地址
     */
    //@NotBlank(message = "讲师介绍不能为空")
    private String lecturer;

    /**
     * 日程介绍图片地址
     */
    //@NotBlank(message = "日程介绍不能为空")
    private String schedule;

    /**
     * 往期介绍图片地址
     */
    //@NotBlank(message = "往期介绍不能为空")
    private String previous;

    /**
     * 地址
     */
    @NotBlank(message = "地址不能为空")
    private String site;

    /**
     * 特训开始时间
     */
    @NotNull(message = "特训开始时间不能为空")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("beginTime")
    private LocalDateTime beginTime;

    /**
     * 特训结束时间
     */
    @NotNull(message = "特训结束时间不能为空")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("endTime")
    private LocalDateTime endTime;

    /**
     * 报名截止时间
     */
    @NotNull(message = "报名截止时间不能为空")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deadline;

    /**
     * 预算人数
     */
    @NotNull(message = "预算人数不能为空")
    @TableField("numberNum")
    private Integer numberNum;

    /**
     * 报名/申请人数
     */
    @TableField("applyNumber")
    private Integer applyNumber;

    /**
     * 联系电话/咨询电话
     */
    @NotBlank(message = "联系电话不能为空")
    @Length(min = 11,max = 11,message = "电话号码格式不正确")
    private String phone;

    /**
     * 价格
     */
    @NotNull(message = "价格不能为空")
    private BigDecimal price;

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
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JSONField(serialize = false)
    @ApiModelProperty(hidden = true)
    private LocalDateTime createTime;

    @TableField("createBy")
    @JSONField(serialize = false)
    @ApiModelProperty(hidden = true)
    private String createBy;

    @TableField("updateTime")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JSONField(serialize = false)
    @ApiModelProperty(hidden = true)
    private LocalDateTime updateTime;

    @TableField("updateBy")
    @JSONField(serialize = false)
    @ApiModelProperty(hidden = true)
    private String updateBy;

}
