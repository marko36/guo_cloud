package com.jc.cloud.school.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.school.entity.UserCombination;
import com.jc.cloud.school.mapper.UserCombinationMapper;
import com.jc.cloud.school.service.IUserCombinationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户和绘本组合 服务实现类
 * </p>
 *
 * @author chenjian
 * @since 2019-06-14
 */
@Service
public class UserCombinationServiceImpl extends ServiceImpl<UserCombinationMapper, UserCombination> implements IUserCombinationService {


    @Override
    public ObjectRestResponse<List<UserCombination>> getUserCombinationList(String userId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("userId",userId);
        return new ObjectRestResponse<List<UserCombination>>().ok(this.list(wrapper));
    }

    @Override
    public ObjectRestResponse saveUserCombination(UserCombination userCombination) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.save(userCombination) ? response.ok("添加成功") : response.error("添加失败");
    }

    @Override
    public ObjectRestResponse updateUserCombination(UserCombination userCombination) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.updateById(userCombination) ? response.ok("修改成功") : response.error("修改失败");
    }

    @Override
    public ObjectRestResponse deleteUserCombination(Integer id) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.removeById(id) ? response.ok("删除成功") : response.error("删除失败");
    }
}
