package innotech.com.sv.modelosDao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import innotech.com.sv.modelos.Empresa;
import innotech.com.sv.modelos.Servicio;

public interface ServicioDao  extends PagingAndSortingRepository<Servicio, Long>{
	public List<Servicio> findByEmpresa(Empresa empresa);
}
