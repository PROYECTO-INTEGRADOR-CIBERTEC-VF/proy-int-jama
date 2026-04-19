package com.cibertec.jama.controller.venta;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VentaController {

    @GetMapping("/venta")
    public String mainVenta() {
        return "venta/venta";
    }

    @GetMapping("/venta/venta-listado")
    public String ventaListado() {
        return "venta/venta-listado";
    }
}
