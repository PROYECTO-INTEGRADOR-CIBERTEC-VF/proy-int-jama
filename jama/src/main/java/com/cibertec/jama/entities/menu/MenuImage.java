package com.cibertec.jama.entities.menu;

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
public class MenuImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private String menuImagenesId;
    private String alias;
    private String url;
    private String backupUrl;
    private String format;
    private double size;

}
