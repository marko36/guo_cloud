package com.jc.cloud.vo.home;


import java.io.Serializable;
import java.util.Date;

public class ActivityVo implements Serializable {
    /**
     * id
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 教师
     */
    private String teacherId;

    private String uid;

    /**
     * 学生
     */
    private String babyId;

    /**
     * 班级
     */
    private String classId;

    /**
     * logo
     */
    private String activiLogo;

    /**
     * 0待审核1未发布2已发布
     */
    private Integer status;

    private Integer version;

    private Boolean delFlag;

    private Date createTime;

    private Date updateTime;

    private String createBy;

    private String updateBy;
}
