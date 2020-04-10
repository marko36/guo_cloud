package com.jc.cloud.school.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * <p>
 * 文档
 * </p>
 *
 * @author chenjian
 * @since 2019-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sxy_document")
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 资源编号
     */
    private String id;

    @TableField("classifyId")
    @NotBlank(message = "分类不能为空")
    private Integer classifyId;

    /**
     * 标签
     */
    private String tags;

    /**
     * 发布的目标 1.全部 2.仅限家长 3.权限园区 
     */
    @NotBlank(message = "面向人群不能为空")
    private Integer target;

    /**
     * 类型 1.文章  2.视频  3文档
     */
    @NotNull(message = "文件类型不能为空")
    private Integer type;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空")
    private String title;

    /**
     * 内容/介绍
     */
    @NotBlank(message = "介绍不能为空")
    private String content;

    /**
     * 视频来源
     */
    @NotBlank(message = "视频地址不能为空")
    private String source;

    /**
     * 封面图片
     */
    @NotBlank(message = "封面不能为空")
    private String cover;

    /**
     * 视频播放时间
     */
    @JSONField(format = "HH:mm:ss")
    @DateTimeFormat(pattern = "HH:mm:ss")
    @ApiModelProperty(hidden = true)
    private LocalTime time;

    /**
     * 积分
     */
    private Integer score;

    /**
     * 点赞人数
     */
    @TableField("likeCount")
    @ApiModelProperty(hidden = true)
    private Integer likeCount;

    /**
     * 点击人数
     */
    @TableField("clickCount")
    @ApiModelProperty(hidden = true)
    private Integer clickCount;

    /**
     * 分享人数
     */
    @TableField("shareCount")
    @ApiModelProperty(hidden = true)
    private Integer shareCount;

    /**
     * 收藏人数
     */
    @TableField("collectCount")
    @ApiModelProperty(hidden = true)
    private Integer collectCount;

    /**
     * 最后评论时间
     */
    @TableField("lastCommentTime")
    @ApiModelProperty(hidden = true)
    private LocalDateTime lastCommentTime;

    /**
     * 最后评论人
     */
    @TableField("lastCommentBy")
    @ApiModelProperty(hidden = true)
    private String lastCommentBy;

    /**
     * 0待审核 1正常 -1审核失败 
     */
    @ApiModelProperty(hidden = true)
    private Integer status;

    /**
     * 是否删除
     */
    @TableField("delFlag")
    @ApiModelProperty(hidden = true)
    private Boolean delFlag;

    /**
     * 版本号
     */
    @JSONField(serialize = false)
    @ApiModelProperty(hidden = true)
    private Integer version;

    @TableField("createTime")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss",serialize = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(hidden = true)
    private LocalDateTime createTime;

    @TableField("createBy")
    @JSONField(serialize = false)
    @ApiModelProperty(hidden = true)
    private String createBy;

    @TableField("updateTime")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss",serialize = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(hidden = true)
    private LocalDateTime updateTime;

    @TableField("updateBy")
    @JSONField(serialize = false)
    @ApiModelProperty(hidden = true)
    private String updateBy;

}
