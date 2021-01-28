package innotech.com.sv.modelosDao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import innotech.com.sv.modelos.CargosAdicionales;
import innotech.com.sv.modelos.Cliente;
import innotech.com.sv.modelos.Empresa;

public interface ClientesDao  extends PagingAndSortingRepository<Cliente, Long>{

}