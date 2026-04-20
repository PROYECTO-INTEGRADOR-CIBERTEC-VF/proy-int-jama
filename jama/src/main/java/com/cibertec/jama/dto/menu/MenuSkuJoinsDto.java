package com.cibertec.jama.dto.menu;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MenuSkuJoinsDto {

    private int id;
    private String nombre;
    private String descripcion;
    private boolean estaBloqueado;
    private double precio;

    private String typeName;
    private String categoryName;
    private String imageUrl;
    private String imageAlias;

}
