package com.cibertec.jama.controller.menu;


import com.cibertec.jama.dto.menu.MenuSkuAndImageDto;
import com.cibertec.jama.entities.menu.*;
import com.cibertec.jama.service.menu.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class MainMenuController {

    private final MenuService menuService;

    @GetMapping("/menu")
    public String menu(Model model) {
        return "menu/menu";
    }


    @GetMapping("/menu/menu-listado")
    public String menuListado() {
        return "menu/menu-listado";
    }







    /*REGISTRO TIPOS*/

    @GetMapping("/menu/menu-registro-tipos")
    public String menuRegistroTipos(Model model) {
        var types = menuService.getAllTypes();

        model.addAttribute("type", new MenuType());
        model.addAttribute("menuTipos", types);

        return "menu/menu-registro-tipos";
    }

    @PostMapping("/menu/menu-registro-tipos")
    public String menuRegistroTiposSave(MenuType type, Model model) {
        menuService.saveType(type);
        return "redirect:/menu/menu-registro-tipos";
    }

    @PostMapping("/menu/menu-registro-tipos/{id}")
    public String menuRegistroTiposUpdate(MenuType type, @PathVariable int id, Model model) {
        menuService.updateType(type, id);
        return "redirect:/menu/menu-registro-tipos";
    }

    @PostMapping("/menu/menu-registro-tipos/delete/{id}")
    public String menuRegistroTiposDelete(@PathVariable int id, Model model) {

        menuService.deleteType(id);
        return "redirect:/menu/menu-registro-tipos";
    }

    @GetMapping("/menu/menu-registro-tipos/{id}")
    public String menuRegistroTiposUpdate(Model model, @PathVariable int id) {

        var types = menuService.getAllTypes();
        var type = menuService.getTypeById(id);
        model.addAttribute("menuTipos", types);
        model.addAttribute("type", type);

        return "menu/menu-registro-tipos";
    }
    /*REGISTRO DE CATEGORIAS*/

    @GetMapping("/menu/menu-registro-categorias")
    public String menuRegistroCategorias(Model model) {
        var categories = menuService.getAllCategories();
        model.addAttribute("menuCategories", categories);
        model.addAttribute("menuCategory", new MenuCategory());
        return "menu/menu-registro-categorias";
    }

    @PostMapping("/menu/menu-registro-categorias")
    public String menuRegistroCategoriasSave(MenuCategory category, Model model) {
        menuService.saveCategory(category);
        return "redirect:/menu/menu-registro-categorias";
    }

    @PostMapping("/menu/menu-registro-categorias/{id}")
    public String menuRegistroCategoriasUpdate(MenuCategory category, @PathVariable int id, Model model) {
        menuService.updateCategory(category, id);
        return "redirect:/menu/menu-registro-categorias";
    }

    @PostMapping("/menu/menu-registro-categorias/delete/{id}")
    public String menuRegistroCategoriasDelete(@PathVariable int id, Model model) {
        menuService.deleteCategory(id);
        return "redirect:/menu/menu-registro-categorias";
    }

    @GetMapping("/menu/menu-registro-categorias/{id}")
    public String menuRegistroCategoriasUpdate(Model model, @PathVariable int id) {

        var types = menuService.getAllCategories();
        var type = menuService.getCategoriesById(id);
        model.addAttribute("menuCategories", types);
        model.addAttribute("menuCategory", type);

        return "menu/menu-registro-categorias";
    }

    /* REGISTRO DE SKU*/

    @GetMapping("/menu/menu-registro-sku")
    public String menuRegistroSku(Model model) {

        model.addAttribute("menuSkuAndImageDto", new MenuSkuAndImageDto(
                new MenuSku(),
                new MenuImage()
        ));
        model.addAttribute("menuCategories", menuService.getAllCategories());
        model.addAttribute("menuTipos", menuService.getAllTypes());
        model.addAttribute("menuSkuAndImageJoinDto", menuService.getAllSkusAsJoins());

        return "menu/menu-registro-sku";
    }

    @PostMapping("/menu/menu-registro-sku")
    public String menuRegistroSku(MenuSkuAndImageDto menuSkuAndImageDto) {
        menuService.saveImage(menuSkuAndImageDto.menuImage());
        menuService.saveSku(menuSkuAndImageDto.menuSku(), menuSkuAndImageDto.menuImage());

        return "redirect:/menu/menu-registro-sku";
    }

    @GetMapping("/menu/menu-registro-sku/{id}")
    public String menuRegistroSkuUpdate(Model model, @PathVariable int id) {
        var sku = menuService.findSkuById(id);
        if (sku == null){
            return "redirect:/menu/menu-registro-sku";
        }
        var defaultType = sku.getMenuType();
        var defaultCategory= sku.getMenuCategory();

        var image = sku.getMenuImage();

        model.addAttribute("menuSkuAndImageDto", new MenuSkuAndImageDto(
                sku,
                image
        ));
        model.addAttribute("menuCategories", menuService.getAllCategories());
        model.addAttribute("menuCategoriesDefault", defaultCategory);
        model.addAttribute("menuTipos", menuService.getAllTypes());
        model.addAttribute("menuTiposDefault", defaultType);
        model.addAttribute("menuSkuAndImageJoinDto", menuService.getAllSkusAsJoins());

        return "menu/menu-registro-sku";
    }
    @PostMapping("/menu/menu-registro-sku/{id}")
    public String menuRegistroSkuUpdate(MenuSkuAndImageDto dto, @PathVariable int id) {
        menuService.updateSku(id,dto);

        return "redirect:/menu/menu-registro-sku";
    }
    @GetMapping("/menu/menu-registro-sku/delete/{id}")
    public String menuRegistroSkuDelete(@PathVariable int id) {
        menuService.deleteSku(id);

        return "redirect:/menu/menu-registro-sku";
    }

    /*REGISTRO DE MENUS*/

    @GetMapping("/menu/menu-registro-menu")
    public String menuRegistroMenu(Model model) {

        model.addAttribute("menuList", new Menu());
        model.addAttribute("menuSkus", menuService.getAllSkus());
        model.addAttribute("menusTableList", menuService.getAllMenus());
        model.addAttribute("emptySkus", new ArrayList<MenuSku>());

        return "menu/menu-registro-menu";
    }

    @PostMapping("/menu/menu-registro-menu")
    public String menuRegistroMenu(Menu menu,Model model) {
        menuService.saveMenu(menu);
        return "redirect:/menu/menu-registro-menu";
    }

    @GetMapping("/menu/menu-registro-menu/{id}")
    public String menuRegistroMenuUpdate(Model model, @PathVariable int id) {
        var menu = menuService.findMenuBydId(id);

        model.addAttribute("menuList", menu);
        model.addAttribute("menuSkus", menuService.getAllSkus());
        model.addAttribute("menusTableList", menuService.getAllMenus());
        model.addAttribute("emptySkus", new ArrayList<MenuSku>());

        return "menu/menu-registro-menu";
    }

    @GetMapping("/menu/menu-registro-menu/delete/{id}")
    public String menuRegistroMenuDelete(Model model, @PathVariable int id) {
        menuService.deleteMenuById(id);
        return "redirect:/menu/menu-registro-menu";
    }


}





