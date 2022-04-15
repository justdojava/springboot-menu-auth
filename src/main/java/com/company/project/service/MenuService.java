package com.company.project.service;

import com.company.project.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.entity.vo.MenuVo;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author pzblog
 * @since 2020-06-28
 */
public interface MenuService extends IService<Menu> {

    void addMenu(Menu menu);

    List<MenuVo> queryMenuTree();

    List<MenuVo> queryMenus(Long userId);
}
