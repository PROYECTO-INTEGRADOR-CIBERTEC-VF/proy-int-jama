package com.cibertec.jama.controller.pedido;

import com.cibertec.jama.service.pedido.PedidoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/pedido")
    public String mainPedido() {
        return "pedido/pedido";
    }

    @GetMapping("/pedido/registrar-pedido")
    public String registrarPedido() {
        return "pedido/registrar-pedido";
    }

    @GetMapping("/pedido/seguimiento-pedido")
    public String seguimientoPedido(Model model) {
        model.addAttribute("pedidosPendientes", pedidoService.listarPedidosPendientes());
        return "pedido/seguimiento-pedido";
    }
}