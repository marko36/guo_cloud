package com.jc.cloud.school.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.school.entity.Grading;
import com.jc.cloud.school.mapper.GradingMapper;
import com.jc.cloud.school.service.IGradingService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 分级 服务实现类
 * </p>
 *
 * @author chenjian
 * @since 2019-06-14
 */
@Service
public class GradingServiceImpl extends ServiceImpl<GradingMapper, Grading> implements IGradingService {

    @Override
    public ObjectRestResponse<Grading> getGradingById(Integer id) {
        ObjectRestResponse response = new ObjectRestResponse();
        Grading grading = this.getById(id);
        return grading == null ? response.error("不存在") : response.ok(grading);
    }

    @Override
    public ObjectRestResponse<List<Grading>> getListAll() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.select("id,name,ageGroup");
        return new ObjectRestResponse<List<Grading>>().ok(this.list(wrapper));
    }

    @Override
    public ObjectRestResponse saveGrading(Grading grading) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.save(grading) ? response.ok("添加成功") : response.error("添加失败");

    }

    @Override
    public ObjectRestResponse updateGrading(Grading grading) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.updateById(grading) ? response.ok("修改成功") : response.error("修改失败");
    }

    @Override
    public ObjectRestResponse deleteGrading(Integer id) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.removeById(id) ? response.ok("删除成功") : response.error("删除失败");
    }
}
