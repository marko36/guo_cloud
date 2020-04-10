package com.jc.cloud.home.mapper;

import com.jc.cloud.home.entity.Activity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 活动 Mapper 接口
 * </p>
 *
 * @author lq
 * @since 2019-06-19
 */
@Repository
public interface ActivityMapper extends BaseMapper<Activity> {

    @Select("select a.* from ryj_class c left join ryj_activity a on c.id = a.classId where classId = #{classId}")
    List<Activity> activiList(@Param("classId") String classId);
}
