package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author pzblog
 * @since 2020-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("id")
    private Long id;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 菜单编码
     */
    @TableField("menu_code")
    private String menuCode;

    /**
     * 父节点
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 节点类型，1文件夹，2页面，3按钮
     */
    @TableField("node_type")
    private Integer nodeType;

    /**
     * 图标地址
     */
    @TableField("icon_url")
    private String iconUrl;

    /**
     * 排序号
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 页面对应的地址
     */
    @TableField("link_url")
    private String linkUrl;

    /**
     * 层次
     */
    @TableField("level")
    private Integer level;

    /**
     * 树id的路径 整个层次上的路径id，逗号分隔，想要找父节点特别快
     */
    @TableField("path")
    private String path;

    /**
     * 是否删除 1：已删除；0：未删除
     */
    @TableField("is_delete")
    private Integer isDelete;


}
