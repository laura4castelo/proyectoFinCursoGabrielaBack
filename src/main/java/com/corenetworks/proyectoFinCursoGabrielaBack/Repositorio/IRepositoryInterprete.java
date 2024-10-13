package com.corenetworks.proyectoFinCursoGabrielaBack.Repositorio;

import com.corenetworks.proyectoFinCursoGabrielaBack.Modelo.Interprete;

public interface IRepositoryInterprete extends IRepository<Interprete, Integer> {

    Interprete findByNombre(String nombre);

//    @Query(value = "SELECT id_cancion from cancion_inteprete where id_interprete = :idinterprete",nativeQuery = true)
//    List<Cancion> findAllBy
//    @Query(value = "SELECT idCancion " +
//                   "FROM cancion_interprete " +
//                   "WHERE idInterprete= :name",nativeQuery = true)
//    List<Integer> findByIdInterpreteNative(@Param("name")int idInterprete);
//


}
