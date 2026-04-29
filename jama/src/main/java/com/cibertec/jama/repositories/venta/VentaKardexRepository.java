package com.cibertec.jama.repositories.venta;

import com.cibertec.jama.entities.venta.VentaKardex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VentaKardexRepository extends JpaRepository<VentaKardex, Integer> {

    List<VentaKardex> findAllByOrderByFechaVentaDesc();

    boolean existsByPedidoId(int pedidoId);

    @Query("""
            SELECT YEAR(v.fechaVenta), MONTH(v.fechaVenta), COUNT(v.id), SUM(v.montoTotal)
            FROM VentaKardex v
            GROUP BY YEAR(v.fechaVenta), MONTH(v.fechaVenta)
            ORDER BY YEAR(v.fechaVenta) DESC, MONTH(v.fechaVenta) DESC
            """)
    List<Object[]> reporteVentasMensuales();
}