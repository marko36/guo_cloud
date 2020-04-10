package com.jc.cloud.school.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.school.entity.Activity;
import com.jc.cloud.school.mapper.ActivityMapper;
import com.jc.cloud.school.service.IActivityService;
import com.jc.cloud.vo.school.ActivityVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 特训营 服务实现类
 * </p>
 *
 * @author chenjian
 * @since 2019-05-31
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements IActivityService {

    @Override
    public ObjectRestResponse<Activity> getActivityById(Integer id){
        ObjectRestResponse response = new ObjectRestResponse();
        Activity activity = this.getById(id);
        return activity == null ? response.error("没有找到这个特训营") : response.ok(activity);

    }

    /**
     * 查询往期和即将开始的特训营，使用正负月份数进行区分,0位获取全部
     * @param month 月份数
     * @return 查询结果
     */
    @Override
    public ObjectRestResponse<List<Activity>> getListBySymbols(Integer month, Page page) {

        QueryWrapper wrapper = new QueryWrapper();
        if(month.equals(0)){
            return new ObjectRestResponse<List<ActivityVo>>().ok(this.page(page));
        } if(month > 0){//如果月份数为正数即为获取即将开始特训营集合
            wrapper.apply("beginTime BETWEEN NOW() AND DATE_ADD(NOW(),INTERVAL {0} MONTH)",month);
            wrapper.orderByAsc("beginTime");
        }else {//如果为负数即为获取往期特训营
            wrapper.apply("deadline BETWEEN DATE_SUB(NOW(),INTERVAL {0} MONTH) AND NOW()", Math.abs(month));
            wrapper.orderByDesc("beginTime");
        }//使用开始时间做为区分即将开始和往期

        return new ObjectRestResponse<List<ActivityVo>>().ok(this.list(wrapper));
    }

    @Override
    public ObjectRestResponse saveActivity(Activity activity) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.save(activity) ? response.ok("添加成功，等待审核") : response.error("添加失败");
    }

    @Override
    public ObjectRestResponse updateActivity(Activity activity) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.updateById(activity) ? response.ok("修改成功") : response.error("修改失败");
    }

    @Override
    public ObjectRestResponse editStatus(Activity activity) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.updateById(activity) ? response.ok("审核保存成功") : response.error("审核保存失败");
    }

    @Override
    public ObjectRestResponse delete(Integer id) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.removeById(id) ? response.ok("删除成功") : response.error("删除失败");
    }

}
