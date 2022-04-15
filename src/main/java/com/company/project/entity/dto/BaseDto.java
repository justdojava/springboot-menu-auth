package com.company.project.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BaseDto implements Serializable {

    private static final long serialVersionUID = -4559267810907997111L;

    private Long userId;
}
