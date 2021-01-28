package innotech.com.sv.servicios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import innotech.com.sv.modelos.Empresa;
import innotech.com.sv.modelos.Reserva;
import innotech.com.sv.modelos.Servicio;

public interface IServicio {
	public List<Servicio> findAll();

	public Page<Servicio> findAll(Pageable pageable);

	public Servicio findById(Long id);

	public Servicio save(Servicio servicio);

	public void delete(Long id);
	
	public List<Servicio> findByEmpresa(Empresa empresa);
}
