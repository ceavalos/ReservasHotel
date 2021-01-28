package innotech.com.sv.modelosDao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import innotech.com.sv.modelos.Empresa;
import innotech.com.sv.modelos.Habitacion;

public interface HabitacionDao extends PagingAndSortingRepository<Habitacion, Long>{
	 public List<Habitacion> findByEmpresa(Empresa empresa);
	 public Page<Habitacion> findAllByEmpresa(Empresa empresa, Pageable pageable);
}
