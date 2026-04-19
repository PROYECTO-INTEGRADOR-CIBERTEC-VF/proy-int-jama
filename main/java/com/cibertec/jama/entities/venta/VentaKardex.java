package com.cibertec.jama.entities.venta;

import com.cibertec.jama.entities.pedido.Pedido;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class VentaKardex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String metodoDePago;
    private boolean pagoEstaRealizado;
    private String descuentoDado;
    private String descuentoAprobadoPor;
    private double propina;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pedido pedido;

}
