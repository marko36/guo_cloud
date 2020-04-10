package com.jc.cloud.school.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.school.entity.UserCombination;

import java.util.List;

/**
 * <p>
 * 用户和绘本组合 服务类
 * </p>
 *
 * @author chenjian
 * @since 2019-06-14
 */
public interface IUserCombinationService extends IService<UserCombination> {

    ObjectRestResponse<List<UserCombination>> getUserCombinationList(String userId);

    ObjectRestResponse<UserCombination> saveUserCombination(UserCombination userCombination);

    ObjectRestResponse<UserCombination> updateUserCombination(UserCombination userCombination);

    ObjectRestResponse<UserCombination> deleteUserCombination(Integer id);

}
