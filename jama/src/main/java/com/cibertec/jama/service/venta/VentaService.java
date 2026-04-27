package com.cibertec.jama.service.venta;

import com.cibertec.jama.entities.pedido.EstadoPedido;
import com.cibertec.jama.entities.pedido.Pedido;
import com.cibertec.jama.entities.venta.VentaKardex;
import com.cibertec.jama.repositories.pedido.PedidoRepository;
import com.cibertec.jama.repositories.venta.VentaKardexRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VentaService {

    private final VentaKardexRepository ventaKardexRepository;
    private final PedidoRepository pedidoRepository;

    public VentaService(VentaKardexRepository ventaKardexRepository,
                        PedidoRepository pedidoRepository) {
        this.ventaKardexRepository = ventaKardexRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public List<VentaKardex> listarVentas() {
        return ventaKardexRepository.findAllByOrderByFechaVentaDesc();
    }

    public Pedido buscarPedidoParaFinalizar(int pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    public void registrarVenta(int pedidoId,
                               String metodoDePago,
                               String descuentoDado,
                               String descuentoAprobadoPor,
                               double propina) {

        if (ventaKardexRepository.existsByPedidoId(pedidoId)) {
            throw new RuntimeException("Este pedido ya tiene una venta registrada");
        }

        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        VentaKardex venta = new VentaKardex();
        venta.setPedido(pedido);
        venta.setFechaVenta(LocalDateTime.now());
        venta.setMontoTotal(pedido.getPrecioTotal() + propina);
        venta.setMetodoDePago(metodoDePago);
        venta.setPagoEstaRealizado(true);
        venta.setDescuentoDado(descuentoDado);
        venta.setDescuentoAprobadoPor(descuentoAprobadoPor);
        venta.setPropina(propina);

        pedido.setEstadoPedido(EstadoPedido.TERMINADO);
        pedido.setPedidoEstaTerminado(true);

        pedidoRepository.save(pedido);
        ventaKardexRepository.save(venta);
    }
}