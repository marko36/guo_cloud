package com.jc.cloud.basic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 如意家园轮播图
 * </p>
 *
 * @author lgh
 * @since 2019-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ryjy_broadcast")
public class Broadcast implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Integer id;

    /**
     * url地址
     */
    private String url;

    /**
     * 图片说明
     */
    private String content;

    /**
     * 图片路径
     */
    @NotNull
    private String path;

    private Integer version;

    @TableField("delFlag")
    private Integer delFlag;

    @TableField("createTime")
    private LocalDateTime createTime;

    @TableField("updateTime")
    private LocalDateTime updateTime;

    @TableField("createBy")
    private String createBy;

    @TableField("updateBy")
    private String updateBy;


}
