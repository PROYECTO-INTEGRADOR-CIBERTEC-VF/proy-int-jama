package com.cibertec.jama.controller.menu;


import com.cibertec.jama.entities.menu.MenuCategory;
import com.cibertec.jama.entities.menu.MenuType;
import com.cibertec.jama.service.menu.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainMenuController {

    private final MenuService menuService;

    @GetMapping("/menu/menu-listado")
    public String menuListado() {
        return "menu/menu-listado";
    }

    @GetMapping("/menu/menu-registro-menu")
    public String menuRegistroMenu() {
        return "menu/menu-registro-menu";
    }

    @GetMapping("/menu/menu-registro-sku")
    public String menuRegistroSku(Model model) {

        return "menu/menu-registro-sku";
    }

    @GetMapping("/menu/menu-registro-categorias")
    public String menuRegistroCategorias(Model model) {
        var categories = menuService.getAllCategories();
        model.addAttribute("menuCategories", categories);
        model.addAttribute("menuCategory", new MenuCategory());
        return "menu/menu-registro-categorias";
    }

    @GetMapping("/menu/menu-registro-tipos")
    public String menuRegistroTipos(Model model) {
        var types = menuService.getAllTypes();

        model.addAttribute("type", new MenuType());
        model.addAttribute("menuTipos", types);

        return "menu/menu-registro-tipos";
    }


}
