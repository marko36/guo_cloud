package com.jc.cloud.school.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.school.entity.Course;
import com.jc.cloud.school.entity.CourseCombination;
import com.jc.cloud.school.mapper.CourseCombinationMapper;
import com.jc.cloud.school.service.ICourseCombinationService;
import com.jc.cloud.school.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程组合 服务实现类
 * </p>
 *
 * @author chenjian
 * @since 2019-05-31
 */
@Service
public class CourseCombinationServiceImpl extends ServiceImpl<CourseCombinationMapper, CourseCombination>
        implements ICourseCombinationService {

    @Autowired
    ICourseService courseService;

    @Override
    public ObjectRestResponse<CourseCombination> getCourseCombination(Integer id){

        QueryWrapper queryWrapper = new QueryWrapper();
        //queryWrapper.select("id,name,presentationUrl,cover,totalPrice,likeCount,clickCount,shareCount,collectCount,buyCount")
        ObjectRestResponse response = new ObjectRestResponse();
        CourseCombination combination = this.getById(id);
        return combination == null ? response.error("没有这个课程组合") : response.ok(combination);

    }

    @Override
    public ObjectRestResponse<List<CourseCombination>> getCourseCombinationList(IPage page){
        return new ObjectRestResponse<List<CourseCombination>>().ok(this.page(page).getRecords());
    }

    @Override
    public ObjectRestResponse saveCourseCombination(CourseCombination combination){
        ObjectRestResponse response = new ObjectRestResponse();
        return this.save(combination) ? response.ok("添加成功，等待审核") : response.error("添加失败");
    }

    @Override
    public ObjectRestResponse updateCourseCombination(CourseCombination combination){
        ObjectRestResponse response = new ObjectRestResponse();
        return this.updateById(combination) ? response.ok("修改成功") : response.error("修改失败");
    }

    @Override
    public ObjectRestResponse editStatus(CourseCombination combination){
        ObjectRestResponse response = new ObjectRestResponse();
        return this.updateById(combination) ? response.ok("审核成功") : response.error("审核失败");
    }

    @Override
    public ObjectRestResponse delete(Integer id) {
        ObjectRestResponse response = new ObjectRestResponse();
        List<Integer> ids = new ArrayList<>(id);
        return this.del(ids) ? response.ok("删除成功") : response.error("删除失败");
    }

    @Override
    public ObjectRestResponse deleteList(List<Integer> ids) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.del(ids) ? response.ok("删除成功") : response.error("删除失败");
    }

    /**
     * 删除动作，依赖于课程组合的课程及视频都会被删除
     * @param ids   要删除的课程组合编号集合
     * @return
     */
    private boolean del(List<Integer> ids){
        QueryWrapper queryWrapper = new QueryWrapper();

        if(ids.size() == 0 || ids == null){
            return false;
        } else if(ids.size() == 1){
            queryWrapper.select("id").eq("combinationId",ids.get(0));
        } else{
            queryWrapper.select("id").in("id",ids);
        }

        List<Integer> courseIdList = courseService.listObjs(queryWrapper);
        ObjectRestResponse<List<Course>> courseResponse = null;

        if(courseIdList.size()>0){
            courseResponse = courseService.deleteList(courseIdList);
        }

        //courseResponse如果不为空且Success的值为true，就执行删除操作，如果返回值为true就表示成功
        if(courseResponse != null && courseResponse.isSuccess() && this.removeByIds(ids)){
            return true;
        }
        return false;
    }


}
