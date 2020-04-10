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
 * 校园监控配置
 * </p>
 *
 * @author lq
 * @since 2019-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_monitor_config")
public class MonitorConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 设备地址
     */
    @NotBlank(message = "设备地址不能为空")
    private String addrs;

    /**
     * 端口
     */
    @NotBlank(message = "端口不能为空")
    private String port;

    /**
     * 类型
     */
    @NotBlank(message = "类型不能为空")
    private String model;

    /**
     * 学校Id
     */
    private String school;

    /**
     * 是否启用
     */
    @TableField("isEnable")
    private Boolean isEnable;

    /**
     * 设备登录用户名
     */
    @TableField("userName")
    private String userName;

    /**
     * 设备登录密码
     */
    private String password;

    /**
     * 安装时间
     */
    @TableField("erectionTime")
    private LocalDateTime erectionTime;

    /**
     * 安装员
     */
    @TableField("erectionBy")
    @NotBlank(message = "安装员不能为空")
    private String erectionBy;


}
