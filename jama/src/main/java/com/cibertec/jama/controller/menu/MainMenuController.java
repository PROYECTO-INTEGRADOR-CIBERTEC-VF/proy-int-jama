package com.cibertec.jama.controller.menu;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainMenuController {


    @GetMapping("/menu/main-menu")
    public String mainMenu() {
        return "menu/main-menu";
    }
}
