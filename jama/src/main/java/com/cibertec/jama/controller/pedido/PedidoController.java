package com.cibertec.jama.controller.pedido;

import com.cibertec.jama.entities.pedido.EstadoPedido;
import com.cibertec.jama.service.pedido.PedidoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/pedido/{id}/estado")
    public String cambiarEstadoPedido(@PathVariable int id, @RequestParam EstadoPedido estado) {
        pedidoService.cambiarEstadoPedido(id, estado);
        return "redirect:/pedido/seguimiento-pedido";
    }

    @PostMapping("/pedido/item/{id}/terminar")
    public String terminarItem(@PathVariable int id) {
        pedidoService.terminarItem(id);
        return "redirect:/pedido/seguimiento-pedido";
    }

    @PostMapping("/pedido/extra/{id}/terminar")
    public String terminarExtra(@PathVariable int id) {
        pedidoService.terminarExtra(id);
        return "redirect:/pedido/seguimiento-pedido";
    }
}