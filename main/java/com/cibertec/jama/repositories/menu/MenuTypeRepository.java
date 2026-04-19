package com.cibertec.jama.repositories.menu;

import com.cibertec.jama.entities.menu.MenuType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MenuTypeRepository extends JpaRepository<MenuType, Long> {
}
