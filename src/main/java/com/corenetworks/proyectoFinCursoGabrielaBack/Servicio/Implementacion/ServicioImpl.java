package com.corenetworks.proyectoFinCursoGabrielaBack.Servicio.Implementacion;

import com.corenetworks.proyectoFinCursoGabrielaBack.Repositorio.IRepository;
import com.corenetworks.proyectoFinCursoGabrielaBack.Servicio.IServicio;
import java.util.List;
import java.util.Optional;

public abstract class ServicioImpl<T,ID> implements IServicio<T,ID> {


    protected abstract IRepository<T, ID> getRepo();

    public T registrar(T t) throws Exception{
        // TODO Auto-generated method stub
        return getRepo().save(t);
    }

    public T modificar(T t) throws Exception{
        // TODO Auto-generated method stub
        return getRepo().save(t);
    }

    public List<T> listar() throws Exception{
        // TODO Auto-generated method stub
        return getRepo().findAll();
    }

    public T listarPorId(ID id) throws Exception {
        // TODO Auto-generated method stub
        Optional<T> t1 = getRepo().findById(id);
        return t1.isPresent() ? t1.get() : null;
    }

    public void eliminar(ID id) throws Exception{
        // TODO Auto-generated method stub
        getRepo().deleteById(id);

    }

}
