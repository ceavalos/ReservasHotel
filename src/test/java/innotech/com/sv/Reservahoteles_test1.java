package innotech.com.sv;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import innotech.com.sv.modelos.Disponibilidad;
import innotech.com.sv.modelos.Empresa;
import innotech.com.sv.servicios.DisponibilidadImp;
import innotech.com.sv.servicios.EmpresaServiceImp;

@SpringBootTest
public class Reservahoteles_test1 {

	@Autowired
	private DisponibilidadImp dispobilidad;
	
	@Autowired
	private EmpresaServiceImp empresaServ;
	
	@Test
	void Disponibilidad_vacia() {
		
		 //
		 Date fechaini = ParseFecha("2020-01-01");
		 Date fechaFin = ParseFecha("2020-01-01");
		 
		 long empresa = 1;
		 
		 List<Disponibilidad> disponible =  dispobilidad.findOcupacionEmpresa(empresa, fechaini, fechaFin);
		 		 		 
		 assertThat(disponible).isNull();
	}
	
	@Test
	void Disponibilidad_ocupada() {
		
		 //
		 Date fechaini =  ParseFecha("2021-01-01");
		 Date fechaFin = ParseFecha("2021-01-05");
		 
		 long empresa = 1;
		 
		 List<Disponibilidad> disponible =  dispobilidad.findOcupacionEmpresa(empresa, fechaini, fechaFin);
		 
		 for(Disponibilidad dis : disponible ) {
			  System.out.println(" id= " + dis.getId());
		 }
		 
		 assertThat(disponible).isNotNull();
	}
	
	/**
     * Permite convertir un String en fecha (Date).
     * @param fecha Cadena de fecha dd/MM/yyyy
     * @return Objeto Date
     */
    public static Date ParseFecha(String fecha)
    {
        //SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } 
        catch (ParseException ex) 
        {
            System.out.println(ex);
        }
        return fechaDate;
    }
    
    
}
