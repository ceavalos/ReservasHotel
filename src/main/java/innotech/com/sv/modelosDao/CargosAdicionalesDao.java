package innotech.com.sv.modelosDao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import innotech.com.sv.modelos.CargosAdicionales;
import innotech.com.sv.modelos.Empresa;

public interface CargosAdicionalesDao  extends PagingAndSortingRepository<CargosAdicionales, Long>{
	 public List<CargosAdicionales> findByEmpresa(Empresa empresa);
}

