package innotech.com.sv.servicios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import innotech.com.sv.modelos.CargosAdicionales;
import innotech.com.sv.modelos.Disponibilidad;
import innotech.com.sv.modelos.Empresa;

public interface IDisponibilidad {
	public List<Disponibilidad> findAll();

	public Page<Disponibilidad> findAll(Pageable pageable);

	public Disponibilidad findById(Long id);

	public Disponibilidad save(Disponibilidad disponibilidad);

	public void delete(Long id);
	
	public List<Disponibilidad> findByEmpresa(Empresa empresa);
}
