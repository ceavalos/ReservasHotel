package innotech.com.sv.ProcesosServices;

import java.util.Date;

import innotech.com.sv.modelos.Empresa;
import innotech.com.sv.modelos.Habitacion;
import innotech.com.sv.modelos.Reserva;

public interface IReserva {
	public void reservar(Reserva reserva, Empresa empresa, Habitacion habitacion, Date fechaini, Date fechafin) ;
	public void cancelar(Empresa empresa, Reserva reserva) ;
	public void valida_disponibilidad(Empresa empresa, Habitacion habitacion, Date fecha_ini, Date fecha_fin);	
	public void busca_disponibilidad(Empresa empresa, Date fecha_ini, Date fecha_fin) ;
}
