package com.cibertec.jama.repositories.menu;

import com.cibertec.jama.entities.menu.Menu;
import com.cibertec.jama.entities.menu.MenuSku;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {


}
