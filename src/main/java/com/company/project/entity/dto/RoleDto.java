package com.company.project.entity.dto;

import com.company.project.entity.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RoleDto extends Role {

    private Long userId;
}
