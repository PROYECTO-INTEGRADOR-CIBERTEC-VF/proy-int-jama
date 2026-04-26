package com.cibertec.jama.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }
    @GetMapping("/menu/main-menu")
    public String mainMenu() {
        return "main-menu";
    }

}
