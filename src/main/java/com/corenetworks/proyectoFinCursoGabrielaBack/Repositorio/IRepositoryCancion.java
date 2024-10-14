package com.corenetworks.proyectoFinCursoGabrielaBack.Repositorio;

import com.corenetworks.proyectoFinCursoGabrielaBack.Modelo.Cancion;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface IRepositoryCancion extends IRepository<Cancion, Integer> {

    List<Cancion> findByNombre(String nombre);

    List<Cancion> findFirst5ByOrderByDescargasDesc();

    List<Cancion> findFist5ByOrderByIdCancionDesc();

    @Query(value = "SELECT * FROM canciones WHERE id_genero = :name",nativeQuery = true)
    List<Cancion> findByIdGeneroNative(@Param("name") int idGenero);

    void deleteByNombre(String nombreCancion);

    @Query(value ="DELETE FROM cancion_interprete " +
            "WHERE id_cancion=:id",nativeQuery = true)
     void borrarCancionDeTablaOriginada(@Param("id") int id_cancion);

    @Query(value = "SELECT c FROM Cancion c WHERE c.genero.idGenero = :idGenero")
	List<Cancion> findByGenero(@Param("idGenero") Integer idGenero);

    @Transactional
	void deleteByIdCancion(int i);

}
