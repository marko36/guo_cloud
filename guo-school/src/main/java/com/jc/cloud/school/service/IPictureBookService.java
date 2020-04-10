package com.jc.cloud.school.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.school.entity.PictureBook;

import java.util.List;

/**
 * <p>
 * 绘本 服务类
 * </p>
 *
 * @author chenjian
 * @since 2019-06-14
 */
public interface IPictureBookService extends IService<PictureBook> {

    ObjectRestResponse<PictureBook> getPictureBookById(Integer id);

    ObjectRestResponse<List<PictureBook>> getListByCombinationID(Integer combinationID);

    ObjectRestResponse savePictureBook(PictureBook pictureBook);

    ObjectRestResponse editStatus(PictureBook pictureBook);

    ObjectRestResponse updatePictureBook(PictureBook pictureBook);

    ObjectRestResponse deletePictureBook(Integer id);

}
