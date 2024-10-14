package com.corenetworks.proyectoFinCursoGabrielaBack.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name="canciones")

public class Cancion  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCancion;

    @Column(length = 120, nullable = false, unique = true)
    private String nombre;
    @Column(nullable = false)
    private LocalDate fechaCreacion;
    @Column(nullable = false)
    private int duracion;
    @Column(nullable = false)
    private int busquedas;
    @Column(nullable = false)
    private int descargas;

//    @ManyToMany(fetch = FetchType.EAGER)
    @ManyToMany
    @JoinTable(
            name = "cancion_interprete",
            joinColumns = @JoinColumn(name = "id_cancion",nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_Interprete")
    )
    private List<Interprete>interpretes;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_genero")
    private Genero genero;

    public Cancion(String nombre, LocalDate localDate, int duracion, List<Interprete> interpretes, Genero genero) {
        this.nombre = nombre;
        this.fechaCreacion = localDate;
        this.duracion = duracion;
        this.interpretes = interpretes;
        this.genero = genero;
    }
    public Cancion(String nombre,int duracion){
        this.nombre=nombre;
        this.duracion=duracion;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "idCancion=" + idCancion +
                ", nombre='" + nombre + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", duracion=" + duracion +
                ", busquedas=" + busquedas +
                ", descargas=" + descargas +
                ", genero=" + genero +
                '}';
    }
}
