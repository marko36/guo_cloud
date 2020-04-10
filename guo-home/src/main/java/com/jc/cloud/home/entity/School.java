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
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 
 * </p>
 *
 * @author lq
 * @since 2019-06-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sch_school")
public class School implements Serializable {

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
     * 地址
     */
    @NotBlank(message = "地址不能为空")
    private String address;

    /**
     * 电话
     */
    @Length(min = 11,max = 11,message = "电话号码格式不正确")
    private String mobile;

    /**
     * 激活（0未激活1已激活）
     */
    private Boolean activity;

    /**
     * logo
     */
    @TableField("schoolLogo")
    private String schoolLogo;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 用户角色
     */
    private String uid;

    /**
     * 经度
     */
    private String lon;

    /**
     * 纬度
     */
    private String lat;

    /**
     * 性质（0民办幼儿园1公办幼儿园）
     */
    private Integer nature;

    private Integer version;

    @TableField("delFlag")
    private Boolean delFlag;

    /**
     * 短信条数
     */
    @TableField("smsCount")
    private Integer smsCount;

    @TableField("useSmsCount")
    private Integer useSmsCount;

    /**
     * 注册时间
     */
    @TableField("registerTime")
    private LocalDateTime registerTime;

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
