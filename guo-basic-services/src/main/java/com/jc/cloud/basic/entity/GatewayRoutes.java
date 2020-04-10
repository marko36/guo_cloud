package com.jc.cloud.basic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author fangliai
 * @since 2019-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gateway_routes")
public class GatewayRoutes implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 路由id
     */
    private String routeId;

    /**
     * 转发目标uri
     */
    private String routeUri;

    /**
     * 路由执行顺序
     */
    private Integer routeOrder;

    /**
     * 断言字符串集合，字符串结构：[{
                "name":"Path",
                "args":{
                   "pattern" : "/zy/**"
                }
              }]
     */
    private String predicates;

    /**
     * 过滤器字符串集合，字符串结构：{
              	"name":"StripPrefix",
              	 "args":{
              	 	"_genkey_0":"1"
              	 }
              }
     */
    private String filters;

    /**
     * 是否启用
     */
    private Boolean isEbl;

    /**
     * 是否删除
     */
    private Boolean isDel;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;


}
