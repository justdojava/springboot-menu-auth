package com.company.project.web;


import com.company.project.core.annotation.CheckPermissions;
import com.company.project.entity.Role;
import com.company.project.entity.dto.BaseDto;
import com.company.project.entity.dto.RoleDto;
import com.company.project.entity.vo.MenuVo;
import com.company.project.service.RoleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author pzblog
 * @since 2020-06-28
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService;

    @CheckPermissions(value="roleMgr:list")
    @PostMapping(value = "/queryRole")
    public List<Role> queryRole(RoleDto roleDto){
        return roleService.list();
    }

    @CheckPermissions(value="roleMgr:add")
    @PostMapping(value = "/addRole")
    public void addRole(RoleDto roleDto){
        roleService.add(roleDto);
    }

    @CheckPermissions(value="roleMgr:delete")
    @PostMapping(value = "/deleteRole")
    public void deleteRole(RoleDto roleDto){
        roleService.delete(roleDto);
    }
}
