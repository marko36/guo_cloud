package com.jc.cloud.school.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.school.entity.Theme;

import java.util.List;

/**
 * <p>
 * 主题 服务类
 * </p>
 *
 * @author chenjian
 * @since 2019-06-14
 */
public interface IThemeService extends IService<Theme> {

    ObjectRestResponse<Theme> getThemeById(Integer id);

    ObjectRestResponse<List<Theme>> getListAll();

    ObjectRestResponse saveTheme(Theme theme);

    ObjectRestResponse updateTheme(Theme theme);

    ObjectRestResponse deleteTheme(Integer id);


}
