package com.company.project.service.impl;

import com.company.project.entity.Role;
import com.company.project.dao.RoleMapper;
import com.company.project.entity.dto.RoleDto;
import com.company.project.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author pzblog
 * @since 2020-06-28
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public boolean add(RoleDto roleDto) {
        Role entity = new Role();
        BeanUtils.copyProperties(roleDto, entity);
        return super.save(entity);
    }

    @Override
    public boolean delete(RoleDto roleDto) {
        return super.removeById(roleDto.getId());
    }
}
