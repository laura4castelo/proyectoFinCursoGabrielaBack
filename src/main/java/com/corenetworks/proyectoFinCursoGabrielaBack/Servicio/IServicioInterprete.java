package com.corenetworks.proyectoFinCursoGabrielaBack.Servicio;

import com.corenetworks.proyectoFinCursoGabrielaBack.Modelo.Interprete;

public interface IServicioInterprete extends IServicio<Interprete,Integer> {
    Interprete buscaPorNombre(String nombre);

}
