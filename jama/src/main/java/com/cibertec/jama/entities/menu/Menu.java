package com.cibertec.jama.entities.menu;


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
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String nombre;
    private boolean estaBloqueado;
    private double precioTotal;
    private double descuento;

    @OneToMany(mappedBy = "menu",cascade = CascadeType.ALL )
    private List<MenuSku> menuSkus;
}