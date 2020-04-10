package com.jc.cloud.school.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.school.entity.PicturebookCombination;

import java.util.List;

/**
 * <p>
 * 绘本组合 服务类
 * </p>
 *
 * @author chenjian
 * @since 2019-06-14
 */
public interface IPicturebookCombinationService extends IService<PicturebookCombination> {

    ObjectRestResponse<PicturebookCombination> getPicturebookCombinationById(Integer id);

    ObjectRestResponse<List<PicturebookCombination>> getListByPicturebookCombination(PicturebookCombination combination, Page page);

    ObjectRestResponse savePicturebookCombination(PicturebookCombination combination);

    ObjectRestResponse updatePicturebookCombination(PicturebookCombination combination);

    ObjectRestResponse editStatus(PicturebookCombination combination);

    ObjectRestResponse deletePicturebookCombination(Integer id);
}
