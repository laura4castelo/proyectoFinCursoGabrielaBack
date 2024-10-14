package com.corenetworks.proyectoFinCursoGabrielaBack.Servicio;

import com.corenetworks.proyectoFinCursoGabrielaBack.Modelo.Genero;

public interface IServicioGenero extends IServicio<Genero,Integer> {

	Genero buscaGenero(Integer idGenero);

	Genero buscarPorNombre(String tipoDeGenero);


}
