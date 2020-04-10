package com.jc.cloud.basic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 后台管理员
 * </p>
 *
 * @author fangliai
 * @since 2019-06-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("cms_user")
public class CmsUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *  
     */
    private String uid;

    private String nickname;

    private String username;

    private String password;

    private String roles;

    @TableField("createTime")
    private LocalDateTime createTime;

    @TableField("createBy")
    private String createBy;


}
