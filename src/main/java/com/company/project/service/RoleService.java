package com.company.project.service;

import com.company.project.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.entity.dto.RoleDto;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author pzblog
 * @since 2020-06-28
 */
public interface RoleService extends IService<Role> {

    boolean add(RoleDto roleDto);

    boolean delete(RoleDto roleDto);

}
