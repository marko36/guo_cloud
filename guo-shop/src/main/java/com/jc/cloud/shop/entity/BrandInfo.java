package com.jc.cloud.shop.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


/**
 * <p>
 * 品牌详情表
 * </p>
 *
 * @author lgh
 * @since 2019-05-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ryg_brand_info")
public class BrandInfo implements Serializable {

    private static final long serialVersionUID = 1L;



    /**
     * 品牌id
     */
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    /**
     * 品牌名称
     */
    @NotBlank(message = "品牌名称不能为空")
    private String name;

    /**
     * 联系电话
     */
    @Length(min = 11,max = 11,message = "电话号码格式不正确")
    private String telephone;

    /**
     * 品牌logo
     */
    private String logo;

    /**
     * 品牌描述
     */
    private String description;

    /**
     * 品牌状态,0禁用，1启用
     */
    private Boolean status;

    /**
     * 排序
     */
    private Integer order;

    @JSONField(serialize = false)
    @ApiModelProperty(hidden = true)
    private Integer version;

    @TableField("delFlag")
    @JSONField(serialize = false)
    @ApiModelProperty(hidden = true)
    private Integer delFlag;

    @TableField("createTime")
    @ApiModelProperty(hidden = true)
    private Date createTime;

    @TableField("updateTime")
    @JSONField(serialize = false)
    @ApiModelProperty(hidden = true)
    private Date updateTime;

    @TableField("createBy")
    @JSONField(serialize = false)
    @ApiModelProperty(hidden = true)
    private String createBy;

    @TableField("updateBy")
    @JSONField(serialize = false)
    @ApiModelProperty(hidden = true)
    private String updateBy;

}
