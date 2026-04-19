package com.cibertec.jama.controller.login;

import com.cibertec.jama.service.usuario.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private boolean flagIsLoginOn = false;

    @GetMapping("/login")
    public String login(Model model) {
        if (!flagIsLoginOn) {
            userService.initRolTable();
            userService.initPositionTable();
            flagIsLoginOn = true;
        }
        return "login/login";
    }

    @PostMapping("/login/login-auth")
    public String login(
            @RequestParam String loginid,
            @RequestParam String password,
            HttpSession session
    ) {
        boolean bool = userService.validateLoginAuth(loginid, password,session );
        if (bool) {
            flagIsLoginOn = false;
            return "redirect:/menu/main-menu";
        }
        return "redirect:/login";
    }
}
