package com.jc.cloud.school.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jc.cloud.common.util.TreeNode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenjian
 * @since 2019-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sxy_document_classify")
public class DocumentClassify extends TreeNode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *  编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * 类型1私密  2.公开
     */
    @NotNull(message = "类型不能为空")
    private Integer type;

    /**
     * 私密授权的园所
     */
    private String visibles;

    @TableField("parentId")
    @NotNull(message = "父级id不能为空")
    private Integer parentId;

    @NotBlank(message = "资源地址不能为空")
    private String path;

    /**
     * 版本号
     */
    private Integer version;

    @TableField("delFlage")
    @JSONField(serialize = false)
    @ApiModelProperty(hidden = true)
    private Boolean delFlage;

    @TableField("creeateTime")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss",serialize = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(hidden = true)
    private LocalDateTime creeateTime;

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


    @Override
    public Object getTreeId() {
        return id;
    }

}
