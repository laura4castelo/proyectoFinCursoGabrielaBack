package com.corenetworks.proyectoFinCursoGabrielaBack.Servicio.Implementacion;

import com.corenetworks.proyectoFinCursoGabrielaBack.Modelo.Interprete;
import com.corenetworks.proyectoFinCursoGabrielaBack.Repositorio.IRepository;
import com.corenetworks.proyectoFinCursoGabrielaBack.Repositorio.IRepositoryInterprete;
import com.corenetworks.proyectoFinCursoGabrielaBack.Servicio.IServicioInterprete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ServicioInterprete extends ServicioImpl<Interprete,Integer> implements IServicioInterprete {

    @Autowired
    private IRepositoryInterprete iRepositoryInterprete;


    @Override
    protected IRepository<Interprete, Integer> getRepo() {
        return iRepositoryInterprete;
    }

    @Override
    public Interprete buscaPorNombre(String nombre) {
        return iRepositoryInterprete.findByNombre(nombre);
    }
}
