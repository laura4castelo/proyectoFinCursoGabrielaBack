package com.corenetworks.proyectoFinCursoGabrielaBack;

import com.corenetworks.proyectoFinCursoGabrielaBack.Modelo.Cancion;
import com.corenetworks.proyectoFinCursoGabrielaBack.Modelo.Genero;
import com.corenetworks.proyectoFinCursoGabrielaBack.Modelo.Interprete;
import com.corenetworks.proyectoFinCursoGabrielaBack.Servicio.IServicioCancion;
import com.corenetworks.proyectoFinCursoGabrielaBack.Servicio.IServicioGenero;
import com.corenetworks.proyectoFinCursoGabrielaBack.Servicio.IServicioInterprete;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class ProyectoFinCursoGabrielaBackApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinCursoGabrielaBackApplication.class, args);
	}
	@Autowired
	private IServicioInterprete servicioInterprete;

	@Autowired
	private IServicioGenero servicioGenero;

	@Autowired
	private IServicioCancion servicioCancion;

	@Override
	public void run(String... args) throws Exception {
//		Genero rock=new Genero(0,"rock");
//		servicioGenero.registrar(rock);
//
//		Genero folk=new Genero(0,"folk");
//		servicioGenero.registrar(folk);
//
//		Interprete oasis = new Interprete(0,"Oasis",new Date(1970,5,25),"Inglaterra");
//		servicioInterprete.registrar(oasis);
//
//		List<Interprete> interpretes = new ArrayList<>();
//		interpretes.add(oasis);

//		Cancion cancion=new Cancion("Wonderfull"
//				, new Date(1980,4,17)
//				,LocalTime.of(0,3,56)
//				,0,0,0,true,interpretes,rock);
//		
//		Cancion cancion2=new Cancion("Wonderfull2"
//				, new Date(1985,7,25)
//				,LocalTime.of(0,4,56)
//				,0,0,0,true,interpretes,rock);

//		Cancion cancion=new Cancion("Wonderfull"
//				, LocalDate.of( 1980 , 4 , 17 )
//				,120
//				,interpretes,rock);
//
//		Cancion cancion2=new Cancion("Wonderfull2"
//				, LocalDate.of( 1985 , 7 , 25 )
//				,166
//				,interpretes,rock);
//
//		Cancion cancion3=new Cancion("Petete"
//				, LocalDate.of( 1985 , 7 , 25 )
//				,166
//				,interpretes,folk);

//		servicioCancion.registrar(cancion);
//		servicioCancion.registrar(cancion2);
//		servicioCancion.registrar(cancion3);
//
//		System.out.println("LISTADO DE CANCIONES ANTES DEL BORRADO:");
//		servicioCancion.listar().forEach(System.out::println);
//
//		System.out.println("BUSCAR LAS CANCIONES DE CADA INT�RPRETE REGISTRADO:");
//		for (Interprete interprete1 : servicioInterprete.listar()) {
//			System.out.println("Int�rprete: " + interprete1.getNombre());
//			List<Cancion> cancionesList = interprete1.getCanciones();
//			if (cancionesList.isEmpty()) {
//				System.out.println("La lista de canciones est� vac�a...");
//			} else {
//				for (Cancion cancion1 : cancionesList) {
//					System.out.println("Cancion: " + cancion1);
//				}
//			}
//		}

//		borrarCancion("Wonderfull");
//		borrarCancion("Wonderfull2");
//		borrarCancionPorId(1);
//		borrarCancionPorId(2);
//		borrarCancionPorId(3);
//		borrarCancionPorId(4);
//		mostrarCanciones();

//			cancionesList.stream().forEach(System.out::println);
//			interprete1.getCanciones().stream().forEach(System.out::println);
	}

//	@Transactional
//	private void borrarCancionPorId(int id) {
//		servicioCancion.borrarCancionPorId(id);
//
//	}
//
//	private void mostrarCanciones() throws Exception {
//		System.out.println("LISTADO DE CANCIONES:");
//		servicioCancion.listar().forEach(System.out::println);
//
//	}
//
//	private void borrarCancion(String nombre) {
//		System.out.println("BORRAR CANCI�N: " + nombre);
//		servicioCancion.borrarCancionPorNombre(nombre);
//	}
}
