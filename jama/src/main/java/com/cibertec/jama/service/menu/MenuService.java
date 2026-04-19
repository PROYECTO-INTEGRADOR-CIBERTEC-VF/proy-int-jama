package com.cibertec.jama.service.menu;

import com.cibertec.jama.entities.menu.MenuCategory;
import com.cibertec.jama.entities.menu.MenuType;
import com.cibertec.jama.repositories.menu.MenuCategoryRepository;
import com.cibertec.jama.repositories.menu.MenuRepository;
import com.cibertec.jama.repositories.menu.MenuSkuRepository;
import com.cibertec.jama.repositories.menu.MenuTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final MenuTypeRepository menuTypeRepository;
    private final MenuCategoryRepository menuCategoryRepository;
    private final MenuSkuRepository menuSkuRepository;


    public List<MenuType> getAllTypes() {
        return menuTypeRepository.findAll();
    }

    public List<MenuCategory> getAllCategories() {
        return menuCategoryRepository.findAll();
    }
}
