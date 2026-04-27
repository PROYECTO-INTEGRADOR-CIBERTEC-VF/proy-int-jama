package com.cibertec.jama.controller.venta;

import com.cibertec.jama.service.pedido.PedidoService;
import com.cibertec.jama.service.venta.VentaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VentaController {

    private final VentaService ventaService;
    private final PedidoService pedidoService;

    public VentaController(VentaService ventaService, PedidoService pedidoService) {
        this.ventaService = ventaService;
        this.pedidoService = pedidoService;
    }

    @GetMapping("/venta")
    public String mainVenta() {
        return "venta/venta";
    }

    @GetMapping("/venta/venta-listado")
    public String ventaListado(Model model) {
        model.addAttribute("ventas", ventaService.listarVentas());
        return "venta/venta-listado";
    }

    @GetMapping("/venta/finalizar-pedido")
    public String finalizarPedido(Model model) {
        model.addAttribute("pedidos", pedidoService.listarPedidosPendientes());
        return "venta/finalizar-pedido";
    }

    @GetMapping("/venta/finalizar-pedido/{id}")
    public String formularioFinalizarPedido(@PathVariable int id, Model model) {
        model.addAttribute("pedido", ventaService.buscarPedidoParaFinalizar(id));
        return "venta/formulario-finalizar-pedido";
    }

    @PostMapping("/venta/registrar")
    public String registrarVenta(@RequestParam int pedidoId,
                                 @RequestParam String metodoDePago,
                                 @RequestParam(required = false) String descuentoDado,
                                 @RequestParam(required = false) String descuentoAprobadoPor,
                                 @RequestParam(defaultValue = "0") double propina) {

        ventaService.registrarVenta(
                pedidoId,
                metodoDePago,
                descuentoDado,
                descuentoAprobadoPor,
                propina
        );

        return "redirect:/venta/venta-listado?success";
    }
}