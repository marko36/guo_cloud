package com.jc.cloud.school.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.school.entity.PictureBook;
import com.jc.cloud.school.mapper.PictureBookMapper;
import com.jc.cloud.school.service.IPictureBookService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 绘本 服务实现类
 * </p>
 *
 * @author chenjian
 * @since 2019-06-14
 */
@Service
public class PictureBookServiceImpl extends ServiceImpl<PictureBookMapper, PictureBook> implements IPictureBookService {

    @Override
    public ObjectRestResponse<PictureBook> getPictureBookById(Integer id) {
        ObjectRestResponse<PictureBook> response = new ObjectRestResponse();
        PictureBook pictureBook = this.getById(id);
        return pictureBook == null ? response.error("不存在") : response.ok(pictureBook);
    }

    @Override
    public ObjectRestResponse<List<PictureBook>> getListByCombinationID(Integer combinationID) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.select("id,combinationID,picture,chineseSentence,EnglishSentence,voiceFrequency")
                .eq("",combinationID);
        return new ObjectRestResponse<List<PictureBook>>().ok(this.list(wrapper));
    }

    @Override
    public ObjectRestResponse savePictureBook(PictureBook pictureBook) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.save(pictureBook) ? response.ok("添加成功") : response.error("添加失败");
    }

    @Override
    public ObjectRestResponse updatePictureBook(PictureBook pictureBook) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.updateById(pictureBook) ? response.ok("修改成功") : response.error("修改失败");
    }

    @Override
    public ObjectRestResponse editStatus(PictureBook pictureBook) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.updateById(pictureBook) ? response.ok("审核保存成功") : response.error("审核保存失败");
    }

    @Override
    public ObjectRestResponse deletePictureBook(Integer id) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.removeById(id) ? response.ok("删除成功") : response.error("删除失败");
    }
}
