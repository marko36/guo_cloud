package com.jc.cloud.school.entity;

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
 * 用户和绘本
 * </p>
 *
 * @author chenjian
 * @since 2019-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ggxy_user_pictureBook")
public class UserPicturebook implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 组合记录编号
     */
    @TableField("userCombinationId")
    private Integer userCombinationId;

    /**
     * 音频地址
     */
    @TableField("voiceFrequency")
    private String voiceFrequency;

    /**
     * 分数
     */
    private Integer score;

    /**
     * 0待审核 1正常 -1审核失败 
     */
    private Integer status;

    /**
     * 是否删除
     */
    @TableField("delFlag")
    private Boolean delFlag;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createTime;

    /**
     * 创建者
     */
    @TableField("createBy")
    private String createBy;

    /**
     * 更新时间
     */
    @TableField("updateTime")
    private LocalDateTime updateTime;

    /**
     * 更新者
     */
    @TableField("updateBy")
    private String updateBy;


}
