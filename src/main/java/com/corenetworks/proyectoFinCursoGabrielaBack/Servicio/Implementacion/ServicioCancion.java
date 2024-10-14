package com.corenetworks.proyectoFinCursoGabrielaBack.Servicio.Implementacion;
import com.corenetworks.proyectoFinCursoGabrielaBack.Modelo.Cancion;
import com.corenetworks.proyectoFinCursoGabrielaBack.Modelo.Genero;
import com.corenetworks.proyectoFinCursoGabrielaBack.Modelo.Interprete;
import com.corenetworks.proyectoFinCursoGabrielaBack.Repositorio.IRepositoryCancion;
import com.corenetworks.proyectoFinCursoGabrielaBack.Repositorio.IRepositoryInterprete;
import com.corenetworks.proyectoFinCursoGabrielaBack.Repositorio.IRepository;
import com.corenetworks.proyectoFinCursoGabrielaBack.Servicio.IServicioCancion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ServicioCancion extends ServicioImpl<Cancion,Integer> implements IServicioCancion {

    @Autowired
    private IRepositoryCancion iCrudCancionRepositorio;

    @Autowired
    private IRepositoryInterprete iRepositoryInterprete;
    
    @Autowired
    private EntityManager entityManager;

    @Override
    protected IRepository getRepo() {
        return iCrudCancionRepositorio;
    }

    public Cancion incrementoDescarga(Cancion cancion){
        cancion.setDescargas(cancion.getDescargas()+1);
        iCrudCancionRepositorio.save(cancion);
        return cancion;
    }
    public Cancion incrementoBusqueda(Cancion cancion){
        cancion.setBusquedas(cancion.getBusquedas()+1);
        iCrudCancionRepositorio.save(cancion);
        return cancion;
    }
    
    public List<Cancion> porInterprete(String nombre) {

        Interprete interprete=iRepositoryInterprete.findByNombre(nombre);
        String sql="Select * from cancion_interpete where id_interprete ="+interprete.getIdInterprete();
        Query query = entityManager.createNativeQuery(sql);
        List<Cancion> canciones=query.getResultList();
        return canciones;
     }


    // borra todas las que encuentra con ese nombre: OJO, PELIGRO si tienes muchas de clásica
    public void borrarCancionPorNombre(String nombreCancion){
        List<Cancion> cancionList=iCrudCancionRepositorio.findByNombre(nombreCancion);
        for(Cancion cancion : cancionList) {
        	iCrudCancionRepositorio.deleteById(cancion.getIdCancion());
        }

    }

	@Override
	public List<Cancion> buscaPorNombre(String nombreCancion) {
		return iCrudCancionRepositorio.findByNombre(nombreCancion);
	}

	@Override
	public List<Cancion> findByGenero(Genero genero) {

        return iCrudCancionRepositorio.findByGenero(genero.getIdGenero());
	}

    @Override
    public List<Cancion> BuscarLas5MasDescargadas() {
        return iCrudCancionRepositorio.findFirst5ByOrderByDescargasDesc();
    }

    @Override
    public List<Cancion> BuscarLas5MasNuevas() {
        return iCrudCancionRepositorio.findFist5ByOrderByIdCancionDesc();
    }

    @Override
	public void borrarCancionPorId(Integer id) {
		iCrudCancionRepositorio.deleteByIdCancion(id);
		
	}
}
