package com.jc.cloud.school.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.school.entity.PicturebookCombination;
import com.jc.cloud.school.mapper.PicturebookCombinationMapper;
import com.jc.cloud.school.service.IPicturebookCombinationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 绘本组合 服务实现类
 * </p>
 *
 * @author chenjian
 * @since 2019-06-14
 */
@Service
public class PicturebookCombinationServiceImpl extends ServiceImpl<PicturebookCombinationMapper, PicturebookCombination>
        implements IPicturebookCombinationService {


    @Override
    public ObjectRestResponse<PicturebookCombination> getPicturebookCombinationById(Integer id) {
        ObjectRestResponse<PicturebookCombination> response = new ObjectRestResponse<PicturebookCombination>();
        PicturebookCombination combination = this.getById(id);
        return combination == null ? response.error("不存在") : response.ok(combination);
    }

    @Override
    public ObjectRestResponse<List<PicturebookCombination>> getListByPicturebookCombination(PicturebookCombination combination, Page page) {
        ObjectRestResponse<List<PicturebookCombination>> response = new ObjectRestResponse();
        QueryWrapper wrapper = new QueryWrapper();

        wrapper.select("id,gradingId,themeId,cover,title,clickCount");
        wrapper.like("title",combination.getTitle());

        if(combination.getGradingId()!= null){
            wrapper.eq("gradingId",combination.getGradingId());
        }

        if(combination.getThemeId() != null){
            wrapper.eq("themeId",combination.getThemeId());
        }

        return response.ok(this.page(page,wrapper));
    }

    @Override
    public ObjectRestResponse savePicturebookCombination(PicturebookCombination combination) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.save(combination) ? response.ok("添加成功") : response.error("添加失败");
    }

    @Override
    public ObjectRestResponse updatePicturebookCombination(PicturebookCombination combination) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.updateById(combination) ? response.ok("修改成功") : response.error("修改失败");
    }

    @Override
    public ObjectRestResponse editStatus(PicturebookCombination combination) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.updateById(combination) ? response.ok("审核保存成功") : response.error("审核保存失败");
    }

    @Override
    public ObjectRestResponse deletePicturebookCombination(Integer id) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.removeById(id) ? response.ok("删除成功") : response.error("删除失败");
    }
}
