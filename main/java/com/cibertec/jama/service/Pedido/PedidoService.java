package com.cibertec.jama.service.pedido;

import com.cibertec.jama.entities.pedido.Pedido;
import com.cibertec.jama.repositories.pedido.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> listarPedidosPendientes() {
        return pedidoRepository.findByPedidoEstaTerminadoFalseOrderByIdAsc();
    }
}