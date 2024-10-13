package com.corenetworks.proyectoFinCursoGabrielaBack.Controlador;

import com.corenetworks.proyectoFinCursoGabrielaBack.DTO.DtoCancion;
import com.corenetworks.proyectoFinCursoGabrielaBack.DTO.DtoInterprete;
import com.corenetworks.proyectoFinCursoGabrielaBack.Excepciones.ExceptionNoEncontradoModelo;
import com.corenetworks.proyectoFinCursoGabrielaBack.Modelo.Cancion;
import com.corenetworks.proyectoFinCursoGabrielaBack.Modelo.Interprete;
import com.corenetworks.proyectoFinCursoGabrielaBack.Servicio.IServicioInterprete;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/interpretes")

public class ControladorInterprete {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IServicioInterprete iServicioInterprete;

    @GetMapping
    public ResponseEntity<List<DtoInterprete>> consultarTodos() throws Exception{
        return new ResponseEntity<>(convertirAListDto(iServicioInterprete.listar()), HttpStatus.OK);
    }

	@GetMapping("{idInterprete}")
    public ResponseEntity<DtoInterprete> consultarUno(@PathVariable("idInterprete") int idInterprete) throws Exception{		
		Interprete interprete = iServicioInterprete.listarPorId(idInterprete);
		if (interprete == null) {
			throw new ExceptionNoEncontradoModelo("El id: " + idInterprete + " no ha sido encontrado");
		}		
		return new ResponseEntity<>(convertirADto(interprete), HttpStatus.OK);
   }	

    @GetMapping("canciones/{nombreInterprete}")
    public ResponseEntity<List<DtoCancion>> consultaCancionesDeInterprete(@PathVariable("nombreInterprete")String nombreInterprete) throws Exception{
        Interprete interprete= iServicioInterprete.buscaPorNombre(nombreInterprete);
        if (interprete == null){
    		throw new ExceptionNoEncontradoModelo("El intérprete: " + nombreInterprete + " no ha sido encontrado");    		   
        } else {
            return new ResponseEntity<>(convertirACancionesListDto(interprete.getCanciones()), HttpStatus.OK);
        }
    }
    
    @PostMapping
    public ResponseEntity<DtoInterprete> insertar(@Validated @RequestBody DtoInterprete dtoInterprete) throws Exception{
        Interprete interprete = convertirAInterprete(dtoInterprete);
        return new ResponseEntity<>(convertirADto(iServicioInterprete.registrar(interprete)), HttpStatus.CREATED);
    }
    
	@PutMapping
	public ResponseEntity<DtoInterprete> modificar(@Validated @RequestBody DtoInterprete dtoInterprete) throws Exception {
		Interprete interprete = convertirAInterprete(dtoInterprete);
		Interprete interprete1 = iServicioInterprete.listarPorId(interprete.getIdInterprete());
		if (interprete1 == null) {
			throw new ExceptionNoEncontradoModelo("El id: " + interprete.getIdInterprete() + " no ha sido encontrado");
		}
		return new ResponseEntity<>(convertirADto(iServicioInterprete.modificar(interprete)), HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Interprete interprete = iServicioInterprete.listarPorId(id);
		if (interprete == null) {
			throw new ExceptionNoEncontradoModelo("El id: " + id + " no ha sido encontrado");
		} else {
			iServicioInterprete.eliminar(interprete.getIdInterprete());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
    
    //---------------------------------------------------------------------------
    // simplifican código arriba

    private List<DtoInterprete> convertirAListDto(List<Interprete> list) {
		return list.stream().map(x-> mapper.map(x, DtoInterprete.class))
                .collect(Collectors.toList());
	}

    private List<DtoCancion> convertirACancionesListDto(List<Cancion> list) {
		return list.stream().map(x-> mapper.map(x, DtoCancion.class))
                .collect(Collectors.toList());
	}

    private DtoInterprete convertirADto(Interprete interprete) {
		return mapper.map(interprete, DtoInterprete.class);
	}

    private Interprete convertirAInterprete(DtoInterprete dtoGenero) {
		return mapper.map(dtoGenero, Interprete.class);
	}

}
