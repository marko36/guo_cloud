package com.jc.cloud.home.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 乘座
 * </p>
 *
 * @author liuqing
 * @since 2019-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("car_ride")
public class Ride implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    @TableField("babyId")
    @NotBlank(message = "宝宝不能为空")
    private String babyId;

    @TableField("schoolBus")
    @NotBlank(message = "学校不能为空")
    private String schoolBus;

    /**
     * 上车时间
     */
    @TableField("startTime")
    private LocalDateTime startTime;

    @ApiParam(hidden = true)
    private Integer version;

    @TableField("createTime")
    @ApiParam(hidden = true)
    private LocalDateTime createTime;


}
