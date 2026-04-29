package com.cibertec.jama.controller.pedido;

import com.cibertec.jama.entities.pedido.EstadoPedido;
import com.cibertec.jama.service.menu.MenuService;
import com.cibertec.jama.service.pedido.PedidoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PedidoController {

    private final PedidoService pedidoService;
    private final MenuService menuService;

    public PedidoController(PedidoService pedidoService, MenuService menuService) {
        this.pedidoService = pedidoService;
        this.menuService = menuService;
    }

    @GetMapping("/pedido")
    public String mainPedido(Model model) {
        model.addAttribute("pedidos", pedidoService.listarTodos());
        return "pedido/pedido";
    }

    @GetMapping("/pedido/registrar-pedido")
    public String registrarPedido(Model model) {
        model.addAttribute("menus", menuService.getAllMenus());
        model.addAttribute("menuSkus", menuService.getAllSkus());
        return "pedido/registrar-pedido";
    }

    @PostMapping("/pedido/guardar")
    public String guardarPedido(
            @RequestParam String mesa,
            @RequestParam String clienteNombre,
            @RequestParam(required = false) String comentarios,
            @RequestParam(required = false) Integer[] menuIds,
            @RequestParam(required = false) Integer[] menuCantidades,
            @RequestParam(required = false) Integer[] skuIds,
            @RequestParam(required = false) Integer[] skuCantidades
    ) {
        pedidoService.guardarPedido(mesa, clienteNombre, comentarios, menuIds, menuCantidades, skuIds, skuCantidades);
        return "redirect:/pedido?success";
    }

    @GetMapping("/pedido/editar/{id}")
    public String editarPedido(@PathVariable int id, Model model) {
        model.addAttribute("pedido", pedidoService.buscarPorId(id));
        return "pedido/editar-pedido";
    }

    @PostMapping("/pedido/actualizar/{id}")
    public String actualizarPedido(@PathVariable int id,
                                   @RequestParam String mesa,
                                   @RequestParam String clienteNombre,
                                   @RequestParam(required = false) String comentarios) {

        pedidoService.actualizarPedido(id, mesa, clienteNombre, comentarios);
        return "redirect:/pedido?success";
    }

    @PostMapping("/pedido/eliminar/{id}")
    public String eliminarPedido(@PathVariable int id) {
        pedidoService.eliminarPedido(id);
        return "redirect:/pedido";
    }

    @GetMapping("/pedido/seguimiento-pedido")
    public String seguimientoPedido(Model model) {
        model.addAttribute("pedidosPendientes", pedidoService.listarPedidosPendientes());
        return "pedido/seguimiento-pedido";
    }

    @PostMapping("/pedido/actualizar-estado/{id}")
    public String actualizarEstadoPedido(@PathVariable int id,
                                         @RequestParam EstadoPedido estadoPedido) {

        pedidoService.actualizarEstadoPedido(id, estadoPedido);
        return "redirect:/pedido/seguimiento-pedido";
    }
}