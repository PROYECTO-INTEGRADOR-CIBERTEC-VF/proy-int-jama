package com.cibertec.jama.repositories.pedido;

import com.cibertec.jama.entities.pedido.PedidoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoItemRepository extends JpaRepository<PedidoItem, Integer> {
}