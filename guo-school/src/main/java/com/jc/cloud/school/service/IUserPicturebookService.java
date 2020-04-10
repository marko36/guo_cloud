package com.jc.cloud.school.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.school.entity.UserPicturebook;

import java.util.List;

/**
 * <p>
 * 用户和绘本 服务类
 * </p>
 *
 * @author chenjian
 * @since 2019-06-14
 */
public interface IUserPicturebookService extends IService<UserPicturebook> {

    ObjectRestResponse<UserPicturebook> getUserPicturebookById(Integer id);

    ObjectRestResponse<List<UserPicturebook>> getListByUserCombinationId(Integer combinationId);

    ObjectRestResponse saveUserPicturebook(UserPicturebook userPicturebook);

    ObjectRestResponse updateUserPicturebook(UserPicturebook userPicturebook);

    ObjectRestResponse deleteUserPicturebook(Integer id);

}
