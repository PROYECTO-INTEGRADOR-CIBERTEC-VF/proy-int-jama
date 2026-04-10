package com.cibertec.jama.entities.usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class UserRol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String rol;
    private String description;
}
