package com.jc.cloud.shop.entity;

import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 商品规格表
 * </p>
 *
 * @author lgh
 * @since 2019-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ryg_goods_stock")
public class GoodsStock implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    /**
     * 商品id
     */
    @TableField("goodsId")
    @NotEmpty(message = "商品id不能为空")
    private String goodsId;

    /**
     * 商品的规格
     */
    @TableField("goodsAttribute")
    @NotEmpty(message = "商品规格不能为空")
    private String goodsAttribute;

    /**
     * 商品价格
     */
    @NotNull(message = "商品价格不能为空")
    private BigDecimal price;

    /**
     * 库存
     */
    @TableField("stock")
    @NotNull
    private Integer stock;

    @TableField("icon")
    @NotEmpty(message = "商品小图不能为空")
    private String icon;

    @JSONField(serialize = false)
    @ApiModelProperty(hidden = true)
    private Boolean version;

    @TableField("delFlag")
    @JSONField(serialize = false)
    @ApiModelProperty(hidden = true)
    private Boolean delFlag;

    @TableField("createTime")
    @ApiModelProperty(hidden = true)
    private LocalDateTime createTime;

    @TableField("updateTime")
    @JSONField(serialize = false)
    @ApiModelProperty(hidden = true)
    private LocalDateTime updateTime;

    @TableField("createBy")
    @JSONField(serialize = false)
    @ApiModelProperty(hidden = true)
    private String createBy;

    @TableField("updateBy")
    @JSONField(serialize = false)
    @ApiModelProperty(hidden = true)
    private String updateBy;


}
