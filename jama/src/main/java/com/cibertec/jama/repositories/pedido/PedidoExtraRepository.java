package com.cibertec.jama.repositories.pedido;

import com.cibertec.jama.entities.pedido.PedidoExtra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoExtraRepository extends JpaRepository<PedidoExtra, Integer> {
}