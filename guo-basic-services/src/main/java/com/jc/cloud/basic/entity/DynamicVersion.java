package com.jc.cloud.basic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import sun.rmi.runtime.Log;

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
@TableName("dynamic_version")
public class DynamicVersion implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键、自动增长、版本号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long version;
    private String path;
}
