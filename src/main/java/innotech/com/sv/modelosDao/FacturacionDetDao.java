package innotech.com.sv.modelosDao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;


import innotech.com.sv.modelos.Empresa;
import innotech.com.sv.modelos.FacturacionDet;

public interface FacturacionDetDao extends PagingAndSortingRepository<FacturacionDet, Long>{
	 public List<FacturacionDet> findByEmpresa(Empresa empresa);
}
