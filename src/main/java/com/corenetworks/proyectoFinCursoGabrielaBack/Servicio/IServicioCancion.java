package com.corenetworks.proyectoFinCursoGabrielaBack.Servicio;

import java.util.List;

import com.corenetworks.proyectoFinCursoGabrielaBack.Modelo.Cancion;
import com.corenetworks.proyectoFinCursoGabrielaBack.Modelo.Genero;

public interface IServicioCancion extends IServicio<Cancion,Integer> {

	void borrarCancionPorNombre(String string);

	void borrarCancionPorId(Integer id);

	List<Cancion> buscaPorNombre(String nombreCancion);

	List<Cancion> findByGenero(Genero genero);

	List<Cancion> BuscarLas5MasDescargadas();

	List<Cancion> BuscarLas5MasNuevas();


}
