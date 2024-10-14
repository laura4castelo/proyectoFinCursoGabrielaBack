package com.corenetworks.proyectoFinCursoGabrielaBack.Controlador;

import com.corenetworks.proyectoFinCursoGabrielaBack.DTO.DtoCancion;
import com.corenetworks.proyectoFinCursoGabrielaBack.Excepciones.ExceptionNoEncontradoModelo;
import com.corenetworks.proyectoFinCursoGabrielaBack.Modelo.Cancion;
import com.corenetworks.proyectoFinCursoGabrielaBack.Modelo.Genero;
import com.corenetworks.proyectoFinCursoGabrielaBack.Servicio.IServicioCancion;
import com.corenetworks.proyectoFinCursoGabrielaBack.Servicio.IServicioGenero;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/canciones")

public class ControladorCancion {

    @Autowired
    private IServicioCancion iServicioCancion;
    
    @Autowired
    private IServicioGenero iServicioGenero;

    @Autowired
    private ModelMapper mapper;


    @GetMapping
    public ResponseEntity<List<DtoCancion>> consultarTodos() throws Exception{
        return new ResponseEntity<>(convertirAListDto(iServicioCancion.listar()), HttpStatus.OK);
    }

	@GetMapping("{idCancion}")
    public ResponseEntity<DtoCancion> consultarUno(@PathVariable("idCancion") int idCancion) throws Exception{		
		Cancion cancion = iServicioCancion.listarPorId(idCancion);
		if (cancion == null) {
			throw new ExceptionNoEncontradoModelo("El id: " + idCancion + " no ha sido encontrado");
		}		
		return new ResponseEntity<>(convertirADto(cancion), HttpStatus.OK);
   }

    // una misma canción puede tener versiones distintas interpretadas por intérpretes (o directores) distintos (p. ej: en música clásica)
    // Por tanto, al buscar por nombre, pueden aparecer más de 1
	@GetMapping("nombre/{nombreCancion}")
    public ResponseEntity<List<DtoCancion>> consultarUnaCancion(@PathVariable("nombreCancion") String nombreCancion) throws Exception{
		List<Cancion> cancionList = iServicioCancion.buscaPorNombre(nombreCancion);
		HttpStatus estado = cancionList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(convertirAListDto(cancionList), estado);
    }
//

    @GetMapping("genero/{idGenero}")
    public ResponseEntity<List<DtoCancion>> consultaCancionesPorIdGenero(@PathVariable("idGenero") Integer idGenero){
        Genero genero = iServicioGenero.buscaGenero(idGenero);
        if (genero == null){
			throw new ExceptionNoEncontradoModelo("El idGenero: " + idGenero + " no ha sido encontrado");
        }
        else {
            List<Cancion> cancionesList = iServicioCancion.findByGenero(genero);
            return new ResponseEntity<>(convertirAListDto(cancionesList), HttpStatus.OK);
        }
    }


	@GetMapping("genero/nombre/{nombreGenero}")
    public ResponseEntity<List<DtoCancion>> consultaCancionesPorGenero(@PathVariable("nombreGenero")String nombreGenero){
       //Obtengo genero por nombre y compruebo que exista

        Genero genero= iServicioGenero.buscarPorNombre(nombreGenero);
		
        if (genero==null){
            throw new ExceptionNoEncontradoModelo(nombreGenero+" no ha sido encontrado");
           // return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
        //Genero existe
        else {
            List<DtoCancion> canciones_dto=genero.getCanciones()
                    .stream()
                    .map(x->mapper.map(x,DtoCancion.class))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(canciones_dto,HttpStatus.OK);
        }
    }

	@GetMapping("masdescargadas")
	public ResponseEntity<List<DtoCancion>> consultaCancionesMasDescargadas(){
		List<Cancion> cancionesList = iServicioCancion.BuscarLas5MasDescargadas();
		return new ResponseEntity<>(convertirAListDto(cancionesList), HttpStatus.OK);
	}
	@GetMapping("masnuevas")
	public ResponseEntity<List<DtoCancion>> consultaCancionesMasNuevas(){
		List<Cancion> cancionesList = iServicioCancion.BuscarLas5MasNuevas();
		return new ResponseEntity<>(convertirAListDto(cancionesList), HttpStatus.OK);
	}


	@PostMapping
	public ResponseEntity<DtoCancion> insertar(@Validated @RequestBody DtoCancion dtoCancion) throws Exception {
		Cancion cancion = convertirACancion(dtoCancion);
		return new ResponseEntity<>(convertirADto(iServicioCancion.registrar(cancion)), HttpStatus.CREATED);
	}
    
	@PutMapping
	public ResponseEntity<DtoCancion> modificar(@Validated @RequestBody DtoCancion dtoCancion) throws Exception {
		Cancion cancion = convertirACancion(dtoCancion);
		Cancion cancion1 = iServicioCancion.listarPorId(cancion.getIdCancion());
		if (cancion1 == null) {
			throw new ExceptionNoEncontradoModelo("El id: " + cancion.getIdCancion() + " no ha sido encontrado");
		}
		
		return new ResponseEntity<>(convertirADto(iServicioCancion.modificar(cancion)), HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Cancion cancion = iServicioCancion.listarPorId(id);
		if (cancion == null) {
			throw new ExceptionNoEncontradoModelo("El id: " + id + " no ha sido encontrado");
		} else {
			iServicioCancion.eliminar(cancion.getIdCancion());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

    //---------------------------------------------------------------------------
    // simplifican código arriba
    
    private List<DtoCancion> convertirAListDto(List<Cancion> list) {
		return list.stream().map(x-> mapper.map(x, DtoCancion.class))
                .collect(Collectors.toList());
	}

    private DtoCancion convertirADto(Cancion cancion) {
		return mapper.map(cancion, DtoCancion.class);
	}

    private Cancion convertirACancion(DtoCancion dtoCancion) {
		return mapper.map(dtoCancion, Cancion.class);
	}


}
