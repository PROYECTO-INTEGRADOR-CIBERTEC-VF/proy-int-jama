package com.cibertec.jama.entities.pedido;

import com.cibertec.jama.entities.usuario.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String mesa;
    private String comentarios;
    private String clienteNombre;
    private double precioTotal;
    private boolean pedidoEstaTerminado;

    private Double startTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<PedidoItem> pedidoItems;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<PedidoExtra> pedidoExtras;
}
