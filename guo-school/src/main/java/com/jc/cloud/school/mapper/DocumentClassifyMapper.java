package com.jc.cloud.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jc.cloud.school.entity.DocumentClassify;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenjian
 * @since 2019-05-31
 */
public interface DocumentClassifyMapper extends BaseMapper<DocumentClassify> {
    @Select("SELECT * FROM sxy_document_classify")
    List<DocumentClassify> getListAll();

}
