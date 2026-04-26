package com.cibertec.jama.entities.menu;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class MenuSku {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String descripcion;
    private boolean estaBloqueado;
    private double precio;

    @ManyToOne(fetch = FetchType.LAZY)
    private MenuType menuType;

    @ManyToOne(fetch = FetchType.LAZY)
    private MenuCategory menuCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    private MenuImage menuImage;

    @ManyToMany(mappedBy = "menuSkus")
    private List<Menu> menus;
}
