package com.cibertec.jama.repositories.venta;

import com.cibertec.jama.entities.venta.VentaKardex;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VentaKardexRepository extends JpaRepository<VentaKardex, Integer> {

    List<VentaKardex> findAllByOrderByFechaVentaDesc();

    boolean existsByPedidoId(int pedidoId);
}