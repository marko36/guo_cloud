package com.jc.cloud.school.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.school.entity.CourseCombination;

import java.util.List;

/**
 * <p>
 * 课程组合 服务类
 * </p>
 *
 * @author chenjian
 * @since 2019-05-31
 */
public interface ICourseCombinationService extends IService<CourseCombination> {

    ObjectRestResponse<CourseCombination> getCourseCombination(Integer id);

    ObjectRestResponse<List<CourseCombination>> getCourseCombinationList(IPage page);

    ObjectRestResponse saveCourseCombination(CourseCombination courseCombination);

    ObjectRestResponse updateCourseCombination(CourseCombination courseCombination);

    ObjectRestResponse editStatus(CourseCombination courseCombination);

    ObjectRestResponse delete(Integer id);

    ObjectRestResponse deleteList(List<Integer> ids);
}
