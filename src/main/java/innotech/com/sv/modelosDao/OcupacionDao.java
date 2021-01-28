package innotech.com.sv.modelosDao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import innotech.com.sv.modelos.Empresa;
import innotech.com.sv.modelos.Ocupacion;

public interface OcupacionDao  extends PagingAndSortingRepository<Ocupacion, Long>{
	public List<Ocupacion> findByEmpresa(Empresa empresa);
}
