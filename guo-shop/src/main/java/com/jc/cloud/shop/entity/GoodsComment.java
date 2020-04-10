package com.jc.cloud.shop.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lgh
 * @since 2019-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ryg_goods_comment")
public class GoodsComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论表id
     */
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    /**
     * 商品id
     */
    @TableField("goodsId")
    private String goodsId;

    /**
     * 用户Id
     */
    @TableField("userId")
    private String userId;

    /**
     * 评论标题
     */
    private String title;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 审核状态：0未审核，1已审核
     */
    @TableField("auditStatus")
    private Boolean auditStatus;

    @JSONField(serialize = false)
    private Integer version;

    @TableField("delFlag")
    private Integer delFlag;

    @TableField("createTime")
    private LocalDateTime createTime;

    @TableField("updateTime")
    @JSONField(serialize = false)
    private LocalDateTime updateTime;

    @TableField("createBy")
    @JSONField(serialize = false)
    private String createBy;

    @TableField("updateBy")
    @JSONField(serialize = false)
    private String updateBy;


}
