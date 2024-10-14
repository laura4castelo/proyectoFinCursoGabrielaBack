package com.corenetworks.proyectoFinCursoGabrielaBack.Modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="generos")

public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGenero;

//    @Column(length = 60, nullable = false, unique = true)
    @Column(length = 60, nullable = false, unique = true)
    private String tipoGenero;


    @OneToMany(mappedBy = "genero",
            cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE},
            orphanRemoval = true)
    List<Cancion> canciones=new ArrayList<>();

    public Genero(int idGenero, String tipoGenero) {
        this.idGenero=idGenero;
        this.tipoGenero = tipoGenero;
    }

    @Override
    public String toString() {
        return "Genero{" +
                "idGenero=" + idGenero +
                ", tipoGenero='" + tipoGenero + '\'' +
                '}';
    }
}
