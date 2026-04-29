package com.cibertec.jama.repositories.pedido;

import com.cibertec.jama.entities.pedido.PedidoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoItemRepository extends JpaRepository<PedidoItem, Integer> {

    @Query("""
            SELECT pi.menu.nombre, SUM(pi.cantidad)
            FROM PedidoItem pi
            GROUP BY pi.menu.nombre
            ORDER BY SUM(pi.cantidad) DESC
            """)
    List<Object[]> reporteTopMenus();
}