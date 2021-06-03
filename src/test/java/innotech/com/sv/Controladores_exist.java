package innotech.com.sv;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import innotech.com.sv.controladores.ClienteController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Controladores_exist {
 
	@Autowired
	ClienteController cliente;
	
	@Test
	public void contextload() {
		assertThat(cliente).isNotNull();
	}
	
}
