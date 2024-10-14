package com.corenetworks.proyectoFinCursoGabrielaBack.Servicio.Implementacion;

import com.corenetworks.proyectoFinCursoGabrielaBack.Modelo.Genero;
import com.corenetworks.proyectoFinCursoGabrielaBack.Repositorio.IRepository;
import com.corenetworks.proyectoFinCursoGabrielaBack.Repositorio.IRepositoryGenero;
import com.corenetworks.proyectoFinCursoGabrielaBack.Servicio.IServicioGenero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class ServicioGenero extends ServicioImpl<Genero,Integer> implements IServicioGenero {

    @Autowired
    private IRepositoryGenero iCrudGeneroRepositorio;


    @Override
    protected IRepository getRepo() {
        return iCrudGeneroRepositorio;
    }


	@Override
	public Genero buscaGenero(Integer idGenero) {
		return iCrudGeneroRepositorio.findById(idGenero).orElse(null);
	}

    @Override
    public Genero buscarPorNombre(String tipoDeGenero) {
        return iCrudGeneroRepositorio.findByTipoGenero(tipoDeGenero);
    }
}
