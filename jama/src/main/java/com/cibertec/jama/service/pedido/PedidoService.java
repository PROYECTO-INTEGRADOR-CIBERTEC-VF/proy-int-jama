package com.cibertec.jama.service.pedido;

import com.cibertec.jama.entities.menu.Menu;
import com.cibertec.jama.entities.menu.MenuSku;
import com.cibertec.jama.entities.pedido.EstadoPedido;
import com.cibertec.jama.entities.pedido.Pedido;
import com.cibertec.jama.entities.pedido.PedidoExtra;
import com.cibertec.jama.entities.pedido.PedidoItem;
import com.cibertec.jama.repositories.menu.MenuRepository;
import com.cibertec.jama.repositories.menu.MenuSkuRepository;
import com.cibertec.jama.repositories.pedido.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final MenuRepository menuRepository;
    private final MenuSkuRepository menuSkuRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         MenuRepository menuRepository,
                         MenuSkuRepository menuSkuRepository) {
        this.pedidoRepository = pedidoRepository;
        this.menuRepository = menuRepository;
        this.menuSkuRepository = menuSkuRepository;
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAllByOrderByIdAsc();
    }

    public List<Pedido> listarPedidosPendientes() {
        return pedidoRepository.findByEstadoPedidoNotOrderByIdAsc(EstadoPedido.TERMINADO);
    }

    public Pedido buscarPorId(int id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    public void guardarPedido(String mesa,
                              String clienteNombre,
                              String comentarios,
                              Integer[] menuIds,
                              Integer[] menuCantidades,
                              Integer[] skuIds,
                              Integer[] skuCantidades) {

        if (mesa == null || mesa.isBlank()) {
            throw new RuntimeException("Mesa obligatoria");
        }

        if (clienteNombre == null || clienteNombre.isBlank()) {
            throw new RuntimeException("Cliente obligatorio");
        }

        Pedido pedido = new Pedido();
        pedido.setMesa(mesa);
        pedido.setClienteNombre(clienteNombre);
        pedido.setComentarios(comentarios);

        // Estado inicial correcto según HU-03
        pedido.setEstadoPedido(EstadoPedido.PENDIENTE);
        pedido.setPedidoEstaTerminado(false);

        double total = 0;

        List<PedidoItem> items = new ArrayList<>();
        List<PedidoExtra> extras = new ArrayList<>();

        if (menuIds != null) {
            for (int i = 0; i < menuIds.length; i++) {
                Menu menu = menuRepository.findById(menuIds[i])
                        .orElseThrow(() -> new RuntimeException("Menú no encontrado"));

                int cantidad = menuCantidades[i];

                PedidoItem item = new PedidoItem();
                item.setPedido(pedido);
                item.setMenu(menu);
                item.setCantidad(cantidad);
                item.setEstaTerminado(false);

                total += menu.getPrecioTotal() * cantidad;
                items.add(item);
            }
        }

        if (skuIds != null) {
            for (int i = 0; i < skuIds.length; i++) {
                MenuSku menuSku = menuSkuRepository.findById(skuIds[i])
                        .orElseThrow(() -> new RuntimeException("Extra no encontrado"));

                int cantidad = skuCantidades[i];

                PedidoExtra extra = new PedidoExtra();
                extra.setPedido(pedido);
                extra.setMenuSku(menuSku);
                extra.setCantidad(cantidad);
                extra.setEstaTerminado(false);

                total += menuSku.getPrecio() * cantidad;
                extras.add(extra);
            }
        }

        pedido.setPrecioTotal(total);
        pedido.setPedidoItems(items);
        pedido.setPedidoExtras(extras);

        pedidoRepository.save(pedido);
    }

    public void actualizarPedido(int id, String mesa, String clienteNombre, String comentarios) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        pedido.setMesa(mesa);
        pedido.setClienteNombre(clienteNombre);
        pedido.setComentarios(comentarios);

        pedidoRepository.save(pedido);
    }

    public void eliminarPedido(int id) {
        pedidoRepository.deleteById(id);
    }
}