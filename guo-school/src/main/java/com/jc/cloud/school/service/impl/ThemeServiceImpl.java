package com.jc.cloud.school.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.school.entity.Theme;
import com.jc.cloud.school.mapper.ThemeMapper;
import com.jc.cloud.school.service.IThemeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 主题 服务实现类
 * </p>
 *
 * @author chenjian
 * @since 2019-06-14
 */
@Service
public class ThemeServiceImpl extends ServiceImpl<ThemeMapper, Theme> implements IThemeService {

    @Override
    public ObjectRestResponse<Theme> getThemeById(Integer id) {
        ObjectRestResponse response = new ObjectRestResponse();
        Theme theme = this.getById(id);
        return theme == null ? response.error("不存在") : response.ok(theme);
    }

    @Override
    public ObjectRestResponse<List<Theme>> getListAll() {
        return new ObjectRestResponse<Theme>().ok(this.list());
    }

    @Override
    public ObjectRestResponse saveTheme(Theme theme) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.save(theme) ? response.ok("添加成功") : response.error("添加失败");
    }

    @Override
    public ObjectRestResponse updateTheme(Theme theme) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.updateById(theme) ? response.ok("修改成功") : response.error("修改失败");
    }

    @Override
    public ObjectRestResponse deleteTheme(Integer id) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.removeById(id) ? response.ok("删除成功") : response.error("删除失败");
    }
}
