package innotech.com.sv.modelosDao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import innotech.com.sv.modelos.Empresa;
import innotech.com.sv.modelos.FacturacionEnc;

public interface FacturacionEncDao extends PagingAndSortingRepository<FacturacionEnc, Long>{
	 public List<FacturacionEnc> findByEmpresa(Empresa empresa);
}
