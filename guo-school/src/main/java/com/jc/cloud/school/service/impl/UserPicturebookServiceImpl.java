package com.jc.cloud.school.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.school.entity.UserPicturebook;
import com.jc.cloud.school.mapper.UserPicturebookMapper;
import com.jc.cloud.school.service.IUserPicturebookService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户和绘本 服务实现类
 * </p>
 *
 * @author chenjian
 * @since 2019-06-14
 */
@Service
public class UserPicturebookServiceImpl extends ServiceImpl<UserPicturebookMapper, UserPicturebook> implements IUserPicturebookService {

    @Override
    public ObjectRestResponse<UserPicturebook> getUserPicturebookById(Integer id) {
        ObjectRestResponse response = new ObjectRestResponse();
        UserPicturebook userPicturebook = this.getById(id);
        return userPicturebook == null ? response.error("不存在") : response.ok(userPicturebook);
    }

    @Override
    public ObjectRestResponse<List<UserPicturebook>> getListByUserCombinationId(Integer combinationId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("combinationId",combinationId);

        return new ObjectRestResponse<List<UserPicturebook>>().ok(this.list(wrapper));
    }

    @Override
    public ObjectRestResponse<UserPicturebook> saveUserPicturebook(UserPicturebook userPicturebook) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.save(userPicturebook) ? response.ok("添加成功") : response.error("添加失败");
    }

    @Override
    public ObjectRestResponse<UserPicturebook> updateUserPicturebook(UserPicturebook userPicturebook) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.updateById(userPicturebook) ? response.ok("修改成功") : response.error("修改失败");
    }

    @Override
    public ObjectRestResponse<UserPicturebook> deleteUserPicturebook(Integer id) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.removeById(id) ? response.ok("删除成功") : response.error("删除失败");
    }
}
