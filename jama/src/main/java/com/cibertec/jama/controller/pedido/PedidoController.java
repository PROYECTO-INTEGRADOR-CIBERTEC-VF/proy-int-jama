package com.cibertec.jama.controller.pedido;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PedidoController {

    @GetMapping("/pedido")
    public String mainPedido() {
        return "pedido/pedido";
    }

    @GetMapping("/pedido/registrar-pedido")
    public String registrarPedido() {
        return "pedido/registrar-pedido";
    }

    @GetMapping("/pedido/seguimiento-pedido")
    public String seguimientoPedido() {
        return "pedido/seguimiento-pedido";
    }
}
