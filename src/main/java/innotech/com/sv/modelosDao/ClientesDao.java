package innotech.com.sv.modelosDao;


import org.springframework.data.repository.PagingAndSortingRepository;


import innotech.com.sv.modelos.Cliente;


public interface ClientesDao  extends PagingAndSortingRepository<Cliente, Long>{

	
}