package com.cibertec.jama.entities.menu;

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
public class MenuSku {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private String nombre;
    private String descripcion;
    private boolean estaBloqueado;
    private String tipoId;
    private double precio;

    @ManyToOne(fetch = FetchType.LAZY)
    private MenuCategory menuCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    private MenuImage menuImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    @ToString.Exclude
    private Menu menu;
}
