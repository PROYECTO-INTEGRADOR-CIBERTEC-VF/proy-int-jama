package com.cibertec.jama.entities.pedido;

import com.cibertec.jama.entities.menu.MenuSku;
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
public class PedidoExtra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int cantidad;
    private boolean estaTerminado;
    private boolean estaEntregado;

    @ManyToOne(fetch = FetchType.LAZY)
    private MenuSku menuSku;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedidos_id")
    private Pedido pedido;
}
