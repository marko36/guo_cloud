package com.jc.cloud.shop.entity;

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

/**
 * <p>
 * 用户收藏表
 * </p>
 *
 * @author lgh
 * @since 2019-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ryg_user_goods_collect")
public class UserGoodsCollect implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 收藏表主键id
     */
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    /**
     * 用户id
     */
    @NotEmpty(message = "用户Id不能为空")
    @TableField("userId")
    private String userId;

    /**
     * 商品id
     */
    @NotEmpty(message = "商品id不能为空")
    @TableField("goodsId")
    private String goodsId;

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
