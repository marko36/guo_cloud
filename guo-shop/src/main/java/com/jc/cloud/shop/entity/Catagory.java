package com.jc.cloud.shop.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.jc.cloud.common.util.TreeNode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 商品分类表
 * </p>
 *
 * @author lgh
 * @since 2019-05-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ryg_catagory")
public class Catagory extends TreeNode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类目id
     */
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    /**
     * 目录名称
     */
    private String name;

    /**
     * 父级id
     */
    @TableField("parentId")
    private Integer parentId;

    @TableField("parentPath")
    private String parentPath;

    /**
     * 状态，1在用，0停用
     */
    private Integer status;

    /**
     * 目录logo
     */
    private String logo;

    @JSONField(serialize = false)
    @ApiModelProperty(hidden = true)
    private Integer version;

    @TableField("delFlag")
    @JSONField(serialize = false)
    @ApiModelProperty(hidden = true)
    private Integer delFlag;

    @TableField("createTime")
    @ApiModelProperty(hidden = true)
    private LocalDateTime createTime;

    @TableField("updateTime")
    @JSONField(serialize = false)
    @ApiModelProperty(hidden = true)
    private Date updateTime;

    @TableField("createBy")
    @ApiModelProperty(hidden = true)
    @JSONField(serialize = false)
    private String createBy;

    @TableField("updateBy")
    @JSONField(serialize = false)
    @ApiModelProperty(hidden = true)
    private String updateBy;


    @Override
    public Object getTreeId() {
        return Id;
    }
}
