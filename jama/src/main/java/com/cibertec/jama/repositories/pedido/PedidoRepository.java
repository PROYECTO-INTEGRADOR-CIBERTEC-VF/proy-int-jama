package com.cibertec.jama.repositories.pedido;

import com.cibertec.jama.entities.pedido.EstadoPedido;
import com.cibertec.jama.entities.pedido.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findAllByOrderByIdAsc();

    List<Pedido> findByEstadoPedidoNotOrderByIdAsc(EstadoPedido estadoPedido);
}