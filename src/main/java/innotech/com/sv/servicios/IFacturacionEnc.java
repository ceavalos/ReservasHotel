package innotech.com.sv.servicios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import innotech.com.sv.modelos.Empresa;
import innotech.com.sv.modelos.FacturacionEnc;

public interface IFacturacionEnc {
	public List<FacturacionEnc> findAll();

	public Page<FacturacionEnc> findAll(Pageable pageable);

	public FacturacionEnc findById(Long id);

	public FacturacionEnc save(FacturacionEnc facturacionEnc);

	public void delete(Long id);
	
	public List<FacturacionEnc> findByEmpresa(Empresa empresa);
}
