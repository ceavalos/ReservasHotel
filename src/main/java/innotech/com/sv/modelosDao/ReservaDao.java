package innotech.com.sv.modelosDao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import innotech.com.sv.modelos.Empresa;
import innotech.com.sv.modelos.Reserva;

public interface ReservaDao  extends PagingAndSortingRepository<Reserva, Long>{
	public List<Reserva> findByEmpresa(Empresa empresa);
}
