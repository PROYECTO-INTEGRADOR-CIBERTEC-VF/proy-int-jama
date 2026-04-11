package com.cibertec.jama.controller.usuario;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/usuario")
    public String mainUsuario() {
        return "usuario/usuario";
    }
    @GetMapping("/usuario/usuario-listado")
    public String usuarioListado() {
        return "usuario/usuario-listado";
    }
    @GetMapping("/usuario/usuario-registro-cargos")
    public String usuarioRegistroCargos() {
        return "usuario/usuario-registro-cargos";
    }

    @GetMapping("/usuario/usuario-registro")
    public String usuarioRegistro() {
        return "usuario/usuario-registro";
    }
}
