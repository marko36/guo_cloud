package com.jc.cloud.school.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.school.entity.Grading;

import java.util.List;

/**
 * <p>
 * 分级 服务类
 * </p>
 *
 * @author chenjian
 * @since 2019-06-14
 */
public interface IGradingService extends IService<Grading> {

    ObjectRestResponse<Grading> getGradingById(Integer id);

    ObjectRestResponse<List<Grading>> getListAll();

    ObjectRestResponse saveGrading(Grading grading);

    ObjectRestResponse updateGrading(Grading grading);

    ObjectRestResponse deleteGrading(Integer id);

}
