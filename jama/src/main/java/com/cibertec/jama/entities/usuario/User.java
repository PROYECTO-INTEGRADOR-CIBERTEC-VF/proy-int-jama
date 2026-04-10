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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String loginid;
    private String password;
    private boolean estaBloqueado;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserRol userRol;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserData userData;
}
