package com.company.project.dao;

import com.company.project.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author pzblog
 * @since 2020-06-28
 */
public interface MenuMapper extends BaseMapper<Menu> {

    int selectAuthByUserIdAndMenuCode(@Param("userId") Long userId, @Param("menuCode") String menuCode);

}
