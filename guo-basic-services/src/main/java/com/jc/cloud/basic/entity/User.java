package com.jc.cloud.basic.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.omg.CORBA.IDLType;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author fangliai
 * @since 2019-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sso_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(type = IdType.INPUT)
    private String uid;

    /**
     * 用户编号
     */
    private String uno;

    /**
     * 昵称
     */
    @TableField("nickName")
    private String nickName;

    /**
     * 性别
     */
    private String gender;

    /**
     * 头像
     */
    private String portrait;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 账号类型
     */
    private String type;

    /**
     * 微信openid
     */
    @TableField("wxopenId")
    private String wxopenId;

    /**
     * 微信uuid
     */
    private String wxuuid;

    /**
     * 邀请人
     */
    @TableField("Inviter")
    private String Inviter;

    /**
     * 积分
     */
    private Integer score;

    /**
     * 已使用积分
     */
    @TableField("useScore")
    private Integer useScore;

    /**
     * 累计积分
     */
    @TableField("scoreCount")
    private Integer scoreCount;

    /**
     * 金额
     */
    private Integer money;

    /**
     * 已提现金额
     */
    @TableField("useMoney")
    private Integer useMoney;

    /**
     * 累计金额
     */
    @TableField("moneyCount")
    private Integer moneyCount;

    /**
     * 视频有效时间
     */
    @TableField("videoValidTime")
    private LocalDateTime videoValidTime;

    /**
     * 资源有效时间
     */
    @TableField("resoureValidTime")
    private LocalDateTime resoureValidTime;

    /**
     * 累计消费
     */
    @TableField("consumeCount")
    private BigDecimal consumeCount;

    /**
     * 最后登录平台
     */
    @TableField("lastPlatform")
    private String lastPlatform;

    /**
     * 经度
     */
    private String lon;

    /**
     * 纬度
     */
    private String lat;

    /**
     * 城市
     */
    private String city;

    /**
     * 注册时间
     */
    @TableField("registerTime")
    private LocalDateTime registerTime;


}
