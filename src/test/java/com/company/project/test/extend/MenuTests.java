package com.company.project.test.extend;

import com.company.project.entity.Menu;
import com.company.project.service.MenuService;
import com.company.project.test.JunitTester;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MenuTests extends JunitTester {

    @Autowired
    private MenuService menuService;

    @Test
    public void addMenu() throws InterruptedException {
        Menu menu = new Menu();
        menu.setName("角色管理-查询");
        menu.setMenuCode("roleMgr:list");
        menu.setParentId(1593333386337l);
        menu.setNodeType(3);
        menu.setSort(1);
        menuService.addMenu(menu);
        Thread.sleep(2l);

        Menu menu1 = new Menu();
        menu.setName("角色管理-新增");
        menu.setMenuCode("roleMgr:add");
        menu.setParentId(1593333386337l);
        menu.setNodeType(3);
        menu.setSort(2);
        menuService.addMenu(menu);
        Thread.sleep(2l);

        Menu menu2 = new Menu();
        menu.setName("角色管理-删除");
        menu.setMenuCode("roleMgr:delete");
        menu.setParentId(1593333386337l);
        menu.setNodeType(3);
        menu.setSort(3);
        menuService.addMenu(menu);

    }
}
