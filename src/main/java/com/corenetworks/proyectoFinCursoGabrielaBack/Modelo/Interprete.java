package com.corenetworks.proyectoFinCursoGabrielaBack.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="interpretes")

public class Interprete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInterprete;

    @Column(nullable = false, length = 120, unique = true)
    private String nombre;
    @Column(nullable = false)
    private Date fechaCreacion;
    @Column(length = 60, nullable = false)
    private String paisOrigen;

	// el siguiente @JsonIgnore significa que no agregue al json la lista de canciones
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "interpretes",
            cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
    List<Cancion> canciones=new ArrayList<>();

    public Interprete(int idInterprete,String nombre, Date fechaCreacion, String paisOrigen) {
        this.idInterprete=idInterprete;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.paisOrigen = paisOrigen;
    }
    public Interprete(String nombre,Date fechaCreacion, String paisOrigen){
        this.nombre=nombre;
        this.fechaCreacion=fechaCreacion;
        this.paisOrigen=paisOrigen;
    }

    @Override
    public String toString() {
        return "Interprete{" +
                "idInterprete=" + idInterprete +
                ", nombre='" + nombre + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", paisOrigen='" + paisOrigen + '\'' +
                '}';
    }
}
