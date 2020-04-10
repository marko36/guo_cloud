package com.jc.cloud.school.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 资源
 * </p>
 *
 * @author chenjian
 * @since 2019-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ggxy_resource")
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 资源名称
     */
    @NotBlank(message = "名称不能为空")
    @TableField("name")
    private String name;

    /**
     * 分级编号
     */
    @NotBlank(message = "分级编号不能为空")
    @TableField("gradingId")
    private Integer gradingId;

    /**
     * 主题编号
     */
    @NotBlank(message = "主题编号不能为空")
    @TableField("themeId")
    private String themeId;

    /**
     * 封面地址
     */
    @NotBlank(message = "封面图片不能为空")
    private String cover;

    /**
     * 资源地址
     */
    @NotBlank(message = "资源不能为空")
    private String path;

    /**
     * 评分
     */
    private String score;

    /**
     * 点击人数
     */
    @TableField("clickCount")
    private Integer clickCount;

    /**
     * 收藏人数
     */
    @TableField("collectCount")
    private Integer collectCount;

    /**
     * 0待审核 1正常 -1审核失败 
     */
    private Integer status;

    /**
     * 是否删除
     */
    @TableField("delFlag")
    private Boolean delFlag;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createTime;

    /**
     * 创建者
     */
    @TableField("createBy")
    private String createBy;

    /**
     * 更新时间
     */
    @TableField("updateTime")
    private LocalDateTime updateTime;

    /**
     * 更新者
     */
    @TableField("updateBy")
    private String updateBy;


}
