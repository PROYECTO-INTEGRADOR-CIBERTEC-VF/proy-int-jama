package com.cibertec.jama.repositories.pedido;

import com.cibertec.jama.entities.pedido.PedidoExtra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoExtraRepository extends JpaRepository<PedidoExtra, Integer> {

    @Query("""
            SELECT pe.menuSku.nombre, SUM(pe.cantidad)
            FROM PedidoExtra pe
            GROUP BY pe.menuSku.nombre
            ORDER BY SUM(pe.cantidad) DESC
            """)
    List<Object[]> reporteTopExtras();
}