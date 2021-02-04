package innotech.com.sv.modelosDao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import innotech.com.sv.modelos.Empresa;
import innotech.com.sv.modelos.Reserva;
import innotech.com.sv.modelos.TiposHabitacion;

public interface ReservaDao  extends PagingAndSortingRepository<Reserva, Long>{
	public List<Reserva> findByEmpresa(Empresa empresa);
	public Page<Reserva> findAllByEmpresa(Empresa empresa, Pageable pageable);
}
