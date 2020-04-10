package com.jc.cloud.home.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 
 * </p>
 *
 * @author lq
 * @since 2019-06-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sch_leave")
public class Leave implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 宝宝
     */
    @TableField("babyId")
    private String babyId;

    /**
     * 老师
     */
    @TableField("teacherId")
    @NotBlank(message = "老师不能为空")
    private String teacherId;

    /**
     * 1病假2事假
     */
    private Integer type;

    /**
     * 理由
     */
    @NotBlank(message = "理由不能为空")
    private String content;

    /**
     * 请假照片
     */
    @TableField("leaveImg")
    private String leaveImg;

    /**
     * 请假开始时间
     */
    @TableField("startTime")
    private LocalDateTime startTime;

    /**
     * 请假结束时间
     */
    @TableField("endTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//写入解析格式
    @NotNull(message = "请假结束时间不能为空")
    private LocalDateTime endTime;

    /**
     * 0待审批1通过
     */
    private Integer status;

    /**
     * 审核人
     */
    @TableField("checkBy")
    private String checkBy;

    /**
     * 审核时间
     */
    @TableField("checkTime")
    private LocalDateTime checkTime;

    @ApiParam(hidden = true)
    private Integer version;

    @TableField("createTime")
    @ApiParam(hidden = true)
    private LocalDateTime createTime;


}
