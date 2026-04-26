package com.cibertec.jama.service.pedido;

import com.cibertec.jama.entities.pedido.EstadoPedido;
import com.cibertec.jama.entities.pedido.Pedido;
import com.cibertec.jama.entities.pedido.PedidoExtra;
import com.cibertec.jama.entities.pedido.PedidoItem;
import com.cibertec.jama.repositories.pedido.PedidoExtraRepository;
import com.cibertec.jama.repositories.pedido.PedidoItemRepository;
import com.cibertec.jama.repositories.pedido.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoItemRepository pedidoItemRepository;
    private final PedidoExtraRepository pedidoExtraRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         PedidoItemRepository pedidoItemRepository,
                         PedidoExtraRepository pedidoExtraRepository) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoItemRepository = pedidoItemRepository;
        this.pedidoExtraRepository = pedidoExtraRepository;
    }

    public List<Pedido> listarPedidosPendientes() {
        List<Pedido> pedidos = pedidoRepository.findByEstadoPedidoNotOrderByIdAsc(EstadoPedido.TERMINADO);

        if (pedidos.isEmpty()) {
            pedidos = pedidoRepository.findByPedidoEstaTerminadoFalseOrderByIdAsc();
        }

        return pedidos;
    }

    public void cambiarEstadoPedido(int pedidoId, EstadoPedido nuevoEstado) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        pedido.setEstadoPedido(nuevoEstado);
        pedido.setPedidoEstaTerminado(nuevoEstado == EstadoPedido.TERMINADO);

        pedidoRepository.save(pedido);
    }

    public void terminarItem(int itemId) {
        PedidoItem item = pedidoItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item no encontrado"));

        item.setEstaTerminado(true);
        pedidoItemRepository.save(item);

        actualizarEstadoGeneral(item.getPedido());
    }

    public void terminarExtra(int extraId) {
        PedidoExtra extra = pedidoExtraRepository.findById(extraId)
                .orElseThrow(() -> new RuntimeException("Extra no encontrado"));

        extra.setEstaTerminado(true);
        pedidoExtraRepository.save(extra);

        actualizarEstadoGeneral(extra.getPedido());
    }

    private void actualizarEstadoGeneral(Pedido pedido) {
        boolean todosItemsTerminados = pedido.getPedidoItems() == null ||
                pedido.getPedidoItems().stream().allMatch(PedidoItem::isEstaTerminado);

        boolean todosExtrasTerminados = pedido.getPedidoExtras() == null ||
                pedido.getPedidoExtras().stream().allMatch(PedidoExtra::isEstaTerminado);

        boolean tieneItems = pedido.getPedidoItems() != null && !pedido.getPedidoItems().isEmpty();
        boolean tieneExtras = pedido.getPedidoExtras() != null && !pedido.getPedidoExtras().isEmpty();

        if (todosItemsTerminados && todosExtrasTerminados && (tieneItems || tieneExtras)) {
            pedido.setEstadoPedido(EstadoPedido.TERMINADO);
            pedido.setPedidoEstaTerminado(true);
        } else {
            pedido.setEstadoPedido(EstadoPedido.EN_PREPARACION);
            pedido.setPedidoEstaTerminado(false);
        }

        pedidoRepository.save(pedido);
    }
}