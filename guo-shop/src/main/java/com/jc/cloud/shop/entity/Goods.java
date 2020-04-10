package com.jc.cloud.shop.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author lgh
 * @since 2019-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ryg_goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品Id
     */
    @TableId("goodsId")
    private String goodsId;

    /**
     * 商品分类id
     */
    @NotNull(message = "商品分类id不能为空")
    private Integer cid;

    /**
     * 商品名称
     */
    @NotEmpty(message = "商品名称不能为空")
    private String name;

    /**
     * 商品编码,随机生成
     */
    @TableField("goodsNo")
    private String goodsNo;

    @TableField("searchIcon")
    private String searchIcon;

    @TableField("img")
    @NotEmpty(message = "商品图片不能为空")
    private String img;

    /**
     * 商品详情
     */
    @TableField("contents")
    private String contents;

    /**
     * 邮费
     * */
    @TableField("postage")
    private BigDecimal postage;

    /**
     * 关键字
     */
    @TableField(value = "keywords")
    private String keywords;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 点击量
     */
    @TableField("pv")
    private Integer pv;

    /**
     * 审核状态：0未审核，1已审核
     */
    @TableField("auditStatus")
    private Integer auditStatus;

    /**
     * 上架状态，-1：已删除，0：下架 1：上架
     */
    @TableField("publishStatus")
    private Integer publishStatus;

    /**
     * 商品品牌id
     */
    @TableField("brandId")
    private Integer brandId;

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
    @JSONField(serialize = false)
    @ApiModelProperty(hidden = true)
    private String createBy;

    @TableField("updateBy")
    @JSONField(serialize = false)
    @ApiModelProperty(hidden = true)
    private String updateBy;


}
