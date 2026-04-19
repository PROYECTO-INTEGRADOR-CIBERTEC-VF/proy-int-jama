package com.cibertec.jama.repositories.menu;

import com.cibertec.jama.entities.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
}
