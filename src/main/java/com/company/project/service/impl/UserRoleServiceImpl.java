package com.company.project.service.impl;

import com.company.project.entity.UserRole;
import com.company.project.dao.UserRoleMapper;
import com.company.project.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author pzblog
 * @since 2020-06-28
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
