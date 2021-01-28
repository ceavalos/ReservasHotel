package innotech.com.sv.modelosDao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;


import innotech.com.sv.modelos.Disponibilidad;
import innotech.com.sv.modelos.Empresa;

public interface DisponibilidadDao  extends PagingAndSortingRepository<Disponibilidad, Long>{
	 public List<Disponibilidad> findByEmpresa(Empresa empresa);
}

