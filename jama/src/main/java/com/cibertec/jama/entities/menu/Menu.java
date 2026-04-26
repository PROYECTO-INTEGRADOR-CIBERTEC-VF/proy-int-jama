package com.cibertec.jama.entities.menu;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ToString.Exclude

    @ManyToMany
    @JoinTable(
            name = "menu_recipe",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_sku_id")
    )
    private List<MenuSku> menuSkus;
}