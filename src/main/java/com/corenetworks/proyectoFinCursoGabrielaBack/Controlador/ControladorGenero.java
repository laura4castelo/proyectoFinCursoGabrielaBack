package com.corenetworks.proyectoFinCursoGabrielaBack.Controlador;

import com.corenetworks.proyectoFinCursoGabrielaBack.DTO.DtoGenero;
import com.corenetworks.proyectoFinCursoGabrielaBack.Excepciones.ExceptionNoEncontradoModelo;
import com.corenetworks.proyectoFinCursoGabrielaBack.Modelo.Genero;
import com.corenetworks.proyectoFinCursoGabrielaBack.Servicio.IServicioGenero;

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
@RequestMapping("/generos")
public class ControladorGenero {

    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private IServicioGenero iServicioGenero;

    @GetMapping
    public ResponseEntity<List<DtoGenero>> consultarTodos() throws Exception{
        return new ResponseEntity<>(convertirAListDto(iServicioGenero.listar()), HttpStatus.OK);
    }

	@GetMapping("{idGenero}")
    public ResponseEntity<DtoGenero> consultarUno(@PathVariable("idGenero") int idGenero) throws Exception{		
		Genero genero = iServicioGenero.listarPorId(idGenero);
		if (genero == null) {
			throw new ExceptionNoEncontradoModelo("El id: " + idGenero + " no ha sido encontrado");
		}		
		return new ResponseEntity<>(convertirADto(genero), HttpStatus.OK);
   }
	@GetMapping("nombre/{nombre}")
	public ResponseEntity<DtoGenero> getGeneroPorNombre(@PathVariable("nombre") String nombreGenero){
		Genero genero=iServicioGenero.buscarPorNombre(nombreGenero);
		return new ResponseEntity<>(mapper.map(genero, DtoGenero.class),HttpStatus.OK);
	}
	
    @PostMapping
    public ResponseEntity<DtoGenero> insertar(@Validated @RequestBody DtoGenero dtoGenero) throws Exception{
        Genero genero = convertirAGenero(dtoGenero);
        
//        if (iServicioGenero.buscaGenero(genero.getIdGenero()) != null){	// ya existe, no se da de alta...
//            return new ResponseEntity<>(convertirADto(null), HttpStatus.CREATED);
//        }
//        else {
            return new ResponseEntity<>(convertirADto(iServicioGenero.registrar(genero)), HttpStatus.CREATED);
//        }
    }
    
	@PutMapping
    public ResponseEntity<DtoGenero> modificar(@Validated @RequestBody DtoGenero dtoGenero) throws Exception{
        Genero genero = convertirAGenero(dtoGenero);
        
        Genero genero1 = iServicioGenero.listarPorId(genero.getIdGenero());
		if (genero1 == null) {
			throw new ExceptionNoEncontradoModelo("El id: " + genero.getIdGenero() + " no ha sido encontrado");
		}		
            return new ResponseEntity<>(convertirADto(iServicioGenero.modificar(genero)), HttpStatus.OK);        
    }

	@DeleteMapping("{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Genero cancion = iServicioGenero.listarPorId(id);
		if (cancion == null) {
			throw new ExceptionNoEncontradoModelo("El id: " + id + " no ha sido encontrado");
		} else {
			iServicioGenero.eliminar(cancion.getIdGenero());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
    //---------------------------------------------------------------------------
    // simplifican código arriba

    private List<DtoGenero> convertirAListDto(List<Genero> list) {
		return list.stream().map(x-> mapper.map(x, DtoGenero.class))
                .collect(Collectors.toList());
	}

    private DtoGenero convertirADto(Genero genero) {
		return mapper.map(genero, DtoGenero.class);
	}

    private Genero convertirAGenero(DtoGenero dtoGenero) {
		return mapper.map(dtoGenero, Genero.class);
	}

}
