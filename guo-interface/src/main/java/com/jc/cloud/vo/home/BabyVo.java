package com.jc.cloud.vo.home;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


public class BabyVo implements Serializable {
    /**
     * id
     */
    private String id;

    /**
     * 名字
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 年级
     */
     private String gradeId;
     private String gradeName;

    /**
     * 学校
     */

    private String schoolId;

    private String schoolName;

    /**
     * 班级
     */

    private String classId;

    private String className;

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

    private String headPortrait;

    /**
     * 就读年龄
     */

    private String ageStudy;

    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date birthday;

    /**
     * 0通过1不通过
     */
    private Integer examine;

    /**
     * 学习状态
     */

    private Integer studyStatus;

    private Integer version;


    private Boolean delFlag;


     private Date createTime;


    private Date updateTime;


    private String createBy;
}
