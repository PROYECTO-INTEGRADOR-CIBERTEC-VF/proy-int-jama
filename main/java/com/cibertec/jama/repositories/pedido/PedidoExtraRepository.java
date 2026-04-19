package com.cibertec.jama.repositories.pedido;

import com.cibertec.jama.entities.pedido.PedidoExtra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PedidoExtraRepository extends JpaRepository<PedidoExtra, Long> {
}
