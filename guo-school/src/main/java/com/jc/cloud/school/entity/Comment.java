package com.jc.cloud.school.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 评论
 * </p>
 *
 * @author chenjian
 * @since 2019-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sxy_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 回复评论编号
     */
    @TableField("replyId")
    private Integer replyId;

    /**
     * 文章编号
     */
    @TableField("documentId")
    @NotBlank(message = "依赖的文章编号不能为空")
    private String documentId;

    /**
     * 评论内容
     */
    @NotBlank(message = "评论内容不能为空")
    private String content;

    /**
     * 点赞人数
     */
    @TableField("likeCount")
    private Integer likeCount;

    /**
     * 审核状态，0未审核，1已审核
     */
    @ApiModelProperty(hidden = true)
    private Boolean status;

    @ApiModelProperty(hidden = true)
    private Integer version;

    @TableField("delFlag")
    @ApiModelProperty(hidden = true)
    private Integer delFlag;

    @TableField("createTime")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(hidden = true)
    private LocalDateTime createTime;

    @TableField("updateTime")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(hidden = true)
    private LocalDateTime updateTime;

    @TableField("createBy")
    @ApiModelProperty(hidden = true)
    private String createBy;

    @TableField("updateBy")
    @ApiModelProperty(hidden = true)
    private String updateBy;
}
