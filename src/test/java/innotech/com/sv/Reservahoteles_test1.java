package innotech.com.sv;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import innotech.com.sv.ProcesosServices.Miscelaneos;
import innotech.com.sv.ProcesosServices.ReservaImp;
import innotech.com.sv.modelos.Habitacion;

@SpringBootTest
public class Reservahoteles_test1 {

	
	@Autowired
	private ReservaImp reservaServicio;
		
	//@Test
	void Disponibilidad_porCia() {
		
		 //
		 Date fechaini = Miscelaneos.ParseFecha("2021-02-01");
		 Date fechaFin = Miscelaneos.ParseFecha("2021-02-02");
		 
		 long empresa = 1;
		
		  List<Habitacion> entro = reservaServicio.listado_disponibles(empresa, fechaini, fechaFin);
		 
		 assertThat(entro).isNotNull();
	}
	
    
	//@Test
	void Disponibilidad_porHabitacion() {
		
		 //
		 Date fechaini = Miscelaneos.ParseFecha("2021-05-02");
		 Date fechaFin = Miscelaneos.ParseFecha("2021-05-02");
		 
		 long empresa    = 1;
		 long habitacion = 1;
		 
		
		 boolean resp = reservaServicio.valida_disponibilidad(empresa, habitacion, fechaini, fechaFin);
		
		 
		 assertThat(resp).isTrue();
	}
    
	
	@Test
	void reserva_habitacion() {
		
		 //
		 Date fechaini = Miscelaneos.ParseFecha("2021-05-02");
		 Date fechaFin = Miscelaneos.ParseFecha("2021-05-02");
		 
		 long empresa    = 1;
		 long habitacion = 1;
		 long reserva = 1;
		 
		 System.out.println("Antes de la reserva");
		 reservaServicio.reservar(reserva, empresa, habitacion, fechaini, fechaFin);
		 System.out.println("Luego de la reserva");
		 
		 assertThat(true).isTrue();
	}
	
	
	
}
