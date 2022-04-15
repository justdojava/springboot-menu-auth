package com.company.project.web;


import com.company.project.core.annotation.CheckPermissions;
import com.company.project.entity.dto.BaseDto;
import com.company.project.entity.vo.MenuVo;
import com.company.project.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author pzblog
 * @since 2020-06-28
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 增加权限控制
     * @param baseDto
     * @return
     */
    @PostMapping(value = "/queryMenuTree")
    public List<MenuVo> queryTreeMenu(BaseDto baseDto){
        return menuService.queryMenuTree();
    }


    @PostMapping(value = "/queryMenus")
    public List<MenuVo> queryMenus(BaseDto request){
        return menuService.queryMenus(request.getUserId());
    }
}
