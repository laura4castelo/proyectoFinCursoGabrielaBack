package com.corenetworks.proyectoFinCursoGabrielaBack.Excepciones;

public class ExceptionNoEncontradoModelo extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ExceptionNoEncontradoModelo(String mensaje){
        super(mensaje);
    }
}
