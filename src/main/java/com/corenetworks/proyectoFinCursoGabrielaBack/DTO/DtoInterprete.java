package com.corenetworks.proyectoFinCursoGabrielaBack.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class DtoInterprete {

	@NotNull
	private int idInterprete;

	@NotNull
//    @Min(0)
//    @Max(120)
	private String nombre;

	@NotNull
	private Date fechaCreacion;

	@NotNull
//    @Min(0)
//    @Max(120)
	private String paisOrigen;

}
