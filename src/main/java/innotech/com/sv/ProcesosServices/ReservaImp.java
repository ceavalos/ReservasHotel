package innotech.com.sv.ProcesosServices;

import java.util.Date;

import org.springframework.stereotype.Service;

import innotech.com.sv.modelos.Empresa;
import innotech.com.sv.modelos.Habitacion;
import innotech.com.sv.modelos.Reserva;

@Service
public class ReservaImp implements IReserva {

	@Override
	public void reservar(Reserva reserva, Empresa empresa, Habitacion habitacion, Date fechaini, Date fechafin) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancelar(Empresa empresa, Reserva reserva) {
		// TODO Auto-generated method stub

	}

	@Override
	public void valida_disponibilidad(Empresa empresa, Habitacion habitacion, Date fecha_ini, Date fecha_fin) {
		// TODO Auto-generated method stub

	}

	@Override
	public void busca_disponibilidad(Empresa empresa, Date fecha_ini, Date fecha_fin) {
		// TODO Auto-generated method stub

	}

}
