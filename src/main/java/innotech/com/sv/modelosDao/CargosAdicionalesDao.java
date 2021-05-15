package innotech.com.sv.modelosDao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import innotech.com.sv.modelos.CargosAdicionales;
import innotech.com.sv.modelos.Empresa;
import innotech.com.sv.modelos.Servicio;

public interface CargosAdicionalesDao  extends PagingAndSortingRepository<CargosAdicionales, Long>{
	 public List<CargosAdicionales> findByEmpresa(Empresa empresa);
	 
	 @Query(value ="select * from cargosadicionales p where p.empresa_id =?1 and p.estado=0", nativeQuery = true )
	 public Page<CargosAdicionales> findAllByEmpresa(Empresa empresa, Pageable pageable);
	 

	 
}

