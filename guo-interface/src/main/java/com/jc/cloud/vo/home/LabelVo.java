package com.jc.cloud.vo.home;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

public class LabelVo implements Serializable {
    /**
     * id
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 老师
     */
    private String teacherId;

    /**
     * 家长
     */
    private String parentId;

    private Integer version;

    private Boolean delFlag;

    private Date createTime;

    private Date updateTime;

    private String createBy;

    private String updateBy;
}
