package com.cibertec.jama.repositories.pedido;

import com.cibertec.jama.entities.pedido.PedidoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PedidoItemRepository extends JpaRepository<PedidoItem, Long> {
}
