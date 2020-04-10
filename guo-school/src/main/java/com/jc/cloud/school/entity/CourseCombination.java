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
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 课程组合
 * </p>
 *
 * @author chenjian
 * @since 2019-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sxy_course_combination")
public class CourseCombination implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 组合编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称/标题
     */
    @NotBlank(message = "标题不能为空")
    private String name;

    /**
     * 描述/介绍
     */
    @TableField("presentationUrl")
    @NotBlank(message = "描述不能为空")
    private String presentationUrl;

    /**
     * 封面图片
     */
    @TableField("cover")
    @NotBlank(message = "封面不能为空")
    private String cover;

    /**
     * 总价格
     */
    @TableField("totalPrice")
    private BigDecimal totalPrice;

    /**
     * 点赞人数
     */
    @TableField("likeCount")
    private Integer likeCount;

    /**
     * 点击人数
     */
    @TableField("clickCount")
    private Integer clickCount;

    /**
     * 分享人数
     */
    @TableField("shareCount")
    private Integer shareCount;

    /**
     * 收藏人数
     */
    @TableField("collectCount")
    private Integer collectCount;

    /**
     * 购买人数
     */
    @TableField("buyCount")
    private Integer buyCount;

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
