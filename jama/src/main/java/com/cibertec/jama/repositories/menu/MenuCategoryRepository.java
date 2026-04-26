package com.cibertec.jama.repositories.menu;

import com.cibertec.jama.entities.menu.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Integer> {
}
