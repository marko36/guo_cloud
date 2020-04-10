package com.jc.cloud.home.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 校车
 * </p>
 *
 * @author lq
 * @since 2019-06-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("car_school_bus")
public class SchoolBus implements Serializable {

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
     * 学校
     */
    @TableField("schoolId")
    @NotBlank(message = "学校不能为空")
    private String schoolId;

    /**
     * 学校名称
     */
    @TableField("schoolName")
    @NotBlank(message = "学校名称不能为空")
    private String schoolName;

    /**
     * 路线
     */
    @TableField("routeId")
    private Integer routeId;

    /**
     * 纬度
     */
    private String lat;

    /**
     * 经度
     */
    private String lon;

    /**
     * 当前工作Id
     */
    @TableField("currentWorkId")
    private String currentWorkId;

    /**
     * 最后一次工作id
     */
    @TableField("lastWorkId")
    private String lastWorkId;

    /**
     * 角色
     */
    private String uid;

    /**
     * 1正常-1禁用
     */
    @TableField("workStatus")
    private Integer workStatus;

    /**
     * 路线
     */
    private String route;

    /**
     * 座位数
     */
    private Integer seat;

    /**
     * 车牌号
     */
    private String license;

    private String address;

    private Integer version;

    @TableField("createTime")
    private LocalDateTime createTime;

    @TableField("createBy")
    private String createBy;


}
