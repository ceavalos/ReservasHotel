package innotech.com.sv.ProcesosServices;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import innotech.com.sv.modelos.Empresa;
import innotech.com.sv.modelos.Habitacion;
import innotech.com.sv.modelos.Reserva;
import innotech.com.sv.modelos.Servicio;

public interface IReserva {
	public void reservar(long reserva, long empresa, long habitacion, Date fechaini, Date fechafin) ;
	public void cancelar(Empresa empresa, Reserva reserva) ;
	public boolean valida_disponibilidad(long empresa, long habitacion, Date fecha_ini, Date fecha_fin);	
	public List<Habitacion> listado_disponibles(long empresa, Date fecha_ini, Date fecha_fin) ;
	//
	//public List<Servicio> findAll();

	//public Page<Servicio> findAll(Pageable pageable);

	public Reserva findById(Long id);

	public Reserva save(Reserva reserva);

	public void delete(Long id);
	
	public List<Reserva> findByEmpresa(Empresa empresa);
}
