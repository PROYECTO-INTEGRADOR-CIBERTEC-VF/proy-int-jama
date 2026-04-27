package com.cibertec.jama.repositories.menu;

import com.cibertec.jama.entities.menu.Menu;
import com.cibertec.jama.entities.menu.MenuSku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuSkuRepository  extends JpaRepository<MenuSku, Integer> {
    List<MenuSku> findAllByMenus(Menu menus);

}
