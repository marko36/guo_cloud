package com.jc.cloud.home.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
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
 * 宝宝
 * </p>
 *
 * @author lq
 * @since 2019-06-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sch_baby")
public class Baby implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 名字
     */
    @NotBlank(message = "名字不能为空")
    private String name;

    /**
     * 性别
     */
    @NotBlank(message = "性别不能为空")
    private String gender;

    /**
     * 年级
     */
    @TableField("gradeId")
    @NotBlank(message = "年级不能为空")
    private String gradeId;

    /**
     * 学校
     */
    @TableField("schoolId")
    private String schoolId;

    /**
     * 班级
     */
    @TableField("classId")
    @NotBlank(message = "班级不能为空")
    private String classId;

    /**
     * 用户角色
     */
    private String uid;

    /**
     * 激活 0未激活 1已激活
     */
    private Boolean activity;

    /**
     * 头像
     */
    @TableField("headPortrait")
    private String headPortrait;

    /**
     * 就读年龄
     */
    @TableField("ageStudy")
    @NotBlank(message = "就读年龄不能为空")
    private String ageStudy;

    /**
     * 出生日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")//写入解析格式
    //@JSONField(format = "yyyy-MM-dd")//输出格式
    @NotNull(message = "出生日期不能为空")
    private LocalDate birthday;

    /**
     * 0通过1不通过
     */
    private Integer examine;

    @ApiParam(hidden = true)
    private Integer version;

    @TableField("delFlag")
    @ApiParam(hidden = true)
    private Boolean delFlag;

    @TableField("createTime")
    @ApiParam(hidden = true)
    private LocalDateTime createTime;

    @TableField("updateTime")
    @ApiParam(hidden = true)
    private LocalDateTime updateTime;

    @TableField("createBy")
    @ApiParam(hidden = true)
    private String createBy;

    @TableField("updateBy")
    @ApiParam(hidden = true)
    private String updateBy;


}
