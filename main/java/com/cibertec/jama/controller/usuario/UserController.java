package com.cibertec.jama.controller.usuario;

import com.cibertec.jama.dto.usuario.UsuarioDataDto;
import com.cibertec.jama.entities.usuario.UserPosition;
import com.cibertec.jama.repositories.usuario.UserPositionRepository;
import com.cibertec.jama.repositories.usuario.UserRolRepository;
import com.cibertec.jama.service.usuario.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {


    private final UserRolRepository rolRepository;
    private final UserPositionRepository positionRepository;

    private final UserService userService;
    private final UserPositionRepository userPositionRepository;

    @GetMapping("/usuario")
    public String mainUsuario() {
        return "usuario/usuario";
    }

    @GetMapping("/usuario/usuario-listado")
    public String usuarioListado(Model model) {
        model.addAttribute("users", userService.getAllUsers());

        return "usuario/usuario-listado";
    }

    @GetMapping("/usuario/usuario-registro-cargos")
    public String usuarioRegistroCargos(Model model) {

        model.addAttribute("cargos", new UserPosition());
        model.addAttribute("cargosLista", positionRepository.findAll());

        return "usuario/usuario-registro-cargos";
    }

    @PostMapping("/usuario/usuario-registro-cargos")
    public String usuarioRegistroCargos(UserPosition userPosition, Model model) {

        userService.saveCargos(userPosition);

        return "redirect:/usuario/usuario-registro-cargos";
    }

    //delete

    @GetMapping("/usuario/usuario-registro-cargos/delete/{id}")
    public String usuarioCargoDelete(@PathVariable Integer id) {
        positionRepository.deleteById(id);
        return "redirect:/usuario/usuario-registro-cargos";
    }


    //    update
    @GetMapping("/usuario/usuario-registro-cargos/update/{id}")
    public String usuarioCargoUpdate(@PathVariable Integer id, Model model) {
        var optional = userPositionRepository.findById(id);
        if (optional.isEmpty    ()) {
            return "redirect:/usuario/usuario-registro-cargos";
        } else {
            model.addAttribute("position", optional.get());
        }

        return "usuario/usuario-update-cargos";
    }

    @PostMapping("/usuario/usuario-registro-cargos/update/{id}")
    public String usuarioCargoUpdate(UserPosition userPosition, @PathVariable Integer id, Model model) {
        userService.updateUserCargo(userPosition,id);

        return "redirect:/usuario/usuario-registro-cargos";
    }


    @GetMapping("/usuario/usuario-registro")
    public String usuarioRegistro(Model model) {
        model.addAttribute("usuarioDataDto", userService.getUserDataDto());
        return "/usuario/usuario-registro";
    }


    @PostMapping("/usuario/usuario-registro/save")
    public String usuarioRegistroSaveForm(UsuarioDataDto usuarioDataDto) {
        userService.saveUserDataDto(usuarioDataDto);

        return "login/login";
    }

    @GetMapping("/usuario/usuario-listado/delete/{id}")
    public String usuarioListadoDelete(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return "redirect:/usuario/usuario-listado";
    }

    @GetMapping("/usuario/usuario-listado/edit/{id}")
    public String usuarioListadoEdit(@PathVariable Integer id, Model model) {
        var dto = userService.getUserDataById(id);
        model.addAttribute("usuarioDataDto", dto);
        return "usuario/usuario-edicion";
    }

    @PostMapping("/usuario/usuario-registro/edit")
    public String usuarioRegistroEditForm(UsuarioDataDto usuarioDataDto) {
        userService.updateUserDataDto(usuarioDataDto);

        return "redirect:/usuario/usuario-listado";
    }


}
