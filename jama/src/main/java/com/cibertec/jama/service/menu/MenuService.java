package com.cibertec.jama.service.menu;

import com.cibertec.jama.dto.menu.MenuSkuAndImageDto;
import com.cibertec.jama.dto.menu.MenuSkuJoinsDto;
import com.cibertec.jama.entities.menu.*;
import com.cibertec.jama.repositories.menu.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final MenuTypeRepository menuTypeRepository;
    private final MenuCategoryRepository menuCategoryRepository;
    private final MenuSkuRepository menuSkuRepository;
    private final MenuImageRepository menuImageRepository;


    public List<MenuType> getAllTypes() {
        return menuTypeRepository.findAll();
    }

    public List<MenuCategory> getAllCategories() {
        return menuCategoryRepository.findAll();
    }

    public void saveType(MenuType type) {
        if (type != null) {
            menuTypeRepository.save(type);
        }
    }

    public MenuType getTypeById(int id) {
        return menuTypeRepository.findById(id).orElse(null);
    }

    public void updateType(MenuType type, int id) {
        var dbType = menuTypeRepository.findById(id).orElse(null);
        if (dbType != null) {
            dbType.setNombre(type.getNombre());
            dbType.setDescripcion(type.getDescripcion());
            menuTypeRepository.save(dbType);
        }
    }

    public void deleteType(int deleteId) {
        menuTypeRepository.deleteById(deleteId);
    }

    public void initTipos() {
        var types = menuTypeRepository.findAll();
        if (!types.isEmpty()) {
            return;
        }

        var list = new ArrayList<MenuType>();

        var extra = new MenuType();
        extra.setNombre("extra");
        extra.setDescripcion("Extra");

        var postre = new MenuType();
        postre.setNombre("postre");
        postre.setDescripcion("Postre");

        var entrada = new MenuType();
        entrada.setNombre("entrada");
        entrada.setDescripcion("Entrada");

        var ensalada = new MenuType();
        ensalada.setNombre("ensalada");
        ensalada.setDescripcion("Ensalada");

        var main = new MenuType();
        main.setNombre("main");
        main.setDescripcion("Main");

        list.add(extra);
        list.add(postre);
        list.add(entrada);
        list.add(ensalada);
        menuTypeRepository.saveAll(list);
    }


    /*categoria*/
    public void saveCategory(MenuCategory category) {
        menuCategoryRepository.save(category);
    }

    public void updateCategory(MenuCategory category, int id) {
        var dbType = menuTypeRepository.findById(id).orElse(null);
        if (dbType != null) {
            dbType.setNombre(category.getName());
            dbType.setDescripcion(category.getDescription());
            menuTypeRepository.save(dbType);
        }
    }

    public void deleteCategory(int id) {
        menuCategoryRepository.deleteById(id);
    }

    public MenuCategory getCategoriesById(int id) {
        return menuCategoryRepository.findById(id).orElse(null);
    }

    public void initCategories() {
        var categories = menuCategoryRepository.findAll();
        if (!categories.isEmpty()) {
            return;
        }


        var list = new ArrayList<MenuCategory>();

        var pasta = new MenuCategory();
        pasta.setName("pasta");
        pasta.setDescription("Pasta");

        var arroces = new MenuCategory();
        arroces.setName("arroces");
        arroces.setDescription("Arroces");

        var criollos = new MenuCategory();
        criollos.setName("criollos");
        criollos.setDescription("Criollos");

        var bebidas = new MenuCategory();
        bebidas.setName("bebidas");
        bebidas.setDescription("Bebidas");

        var saltados = new MenuCategory();
        saltados.setName("saltados");
        saltados.setDescription("Saltados");

        list.add(pasta);
        list.add(arroces);
        list.add(criollos);
        list.add(bebidas);
        list.add(saltados);
        menuCategoryRepository.saveAll(list);
    }

    /*SKUS*/

    public List<MenuSku> getAllSkus() {
        return menuSkuRepository.findAll();

    }

    public List<MenuSkuJoinsDto> getAllSkusAsJoins() {
        var skus = menuSkuRepository.findAll();
        var dtos = new ArrayList<MenuSkuJoinsDto>();


        for (var sku : skus) {


            var newDto = new MenuSkuJoinsDto();
            newDto.setId(sku.getId());
            newDto.setNombre(sku.getNombre());
            newDto.setDescripcion(sku.getDescripcion());
            newDto.setEstaBloqueado(sku.isEstaBloqueado());
            newDto.setPrecio(sku.getPrecio());


            if (sku.getMenuType() != null) {
                newDto.setTypeName(sku.getMenuType().getNombre());
            } else {
                newDto.setTypeName("No type");
            }

            if (sku.getMenuCategory() != null) {
                newDto.setCategoryName(sku.getMenuCategory().getName());
            } else {
                newDto.setCategoryName("No category");
            }

            if (sku.getMenuImage() != null) {
                newDto.setImageUrl(sku.getMenuImage().getUrl());
                newDto.setImageAlias(sku.getMenuImage().getAlias());
            } else {
                newDto.setImageUrl("");
                newDto.setImageAlias("No image");
            }

            dtos.add(newDto);

        }


        return dtos;
    }

    public void saveImage(MenuImage menuImage) {
        menuImageRepository.save(menuImage);

    }

    public void saveSku(MenuSku menuSku, MenuImage menuImage) {

        menuSku.setMenuImage(menuImage);
        if (menuImage.getAlias().isBlank()){
            menuImage.setAlias(menuSku.getNombre());
        }
        menuSkuRepository.save(menuSku);

    }

    public MenuSku findSkuById(int id) {
        return menuSkuRepository.findById(id).orElse(null);
    }

    public void updateSku(int id, MenuSkuAndImageDto dto) {
        var formSku = dto.menuSku();
        var formImage = dto.menuImage();

        var sku = menuSkuRepository.findById(id).orElse(null);

        assert sku != null : "sku not found";
        var image = menuImageRepository.findById(sku.getMenuImage().getId()).orElse(null);


        assert image != null : "image not found";
        image.setAlias(formImage.getAlias());
        image.setUrl(formImage.getUrl());

        sku.setNombre(formSku.getNombre());
        sku.setDescripcion(formSku.getDescripcion());
        sku.setEstaBloqueado(formSku.isEstaBloqueado());
        sku.setPrecio(formSku.getPrecio());
        sku.setMenuImage(image);

        menuImageRepository.save(image);
        menuSkuRepository.save(sku);


    }

    public void deleteSku(int id) {
        menuSkuRepository.deleteById(id);
    }

    public void saveMenu(Menu menu) {
        var realSkus = menuSkuRepository.findAllById(
                menu.getMenuSkus()
                        .stream()
                        .map(MenuSku::getId)
                        .toList()
        );
        menu.setMenuSkus(realSkus);
        menuRepository.save(menu);
    }

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public Menu findMenuBydId(int id) {
        return menuRepository.findById(id).orElse(null);
    }

    public void deleteMenuById(int id) {
        menuRepository.deleteById(id);
    }
}
