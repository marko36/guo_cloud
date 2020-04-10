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
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 司机
 * </p>
 *
 * @author lq
 * @since 2019-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("car_driver")
public class Driver implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotNull(message = "年龄不能为空")
    private Integer age;

    @Length(min = 11,max = 11,message = "电话号码格式不正确")
    private String phone;

    @NotBlank(message = "上岗证不能为空")
    private String photo;

    /**
     * 位置
     */
    private String position;

    @TableField("schoolBusId")
    private String schoolBusId;

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
