package com.corenetworks.proyectoFinCursoGabrielaBack.Repositorio;

import com.corenetworks.proyectoFinCursoGabrielaBack.Modelo.Genero;

public interface IRepositoryGenero extends IRepository<Genero, Integer> {
    Genero findByTipoGenero(String tipoDeGenero);

}
