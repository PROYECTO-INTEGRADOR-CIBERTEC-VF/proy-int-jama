package com.cibertec.jama.entities.usuario;

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
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String loginid;
    private String password;
    private boolean estaBloqueado;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private UserRol userRol;

    @ToString.Exclude
    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserData userData;
}
