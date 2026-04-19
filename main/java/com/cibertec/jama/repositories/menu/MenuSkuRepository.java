package com.cibertec.jama.repositories.menu;

import com.cibertec.jama.entities.menu.MenuSku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MenuSkuRepository  extends JpaRepository<MenuSku, Long> {
}
