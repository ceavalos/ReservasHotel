package innotech.com.sv.controladores;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import innotech.com.sv.modelos.Activo;
import innotech.com.sv.modelos.Empresa;
import innotech.com.sv.modelos.Habitacion;
import innotech.com.sv.modelos.Ocupacion;
import innotech.com.sv.modelos.Promocion;
import innotech.com.sv.paginator.PageRender;
import innotech.com.sv.servicios.OcupacionImp;

@Controller
@RequestMapping("/ocupacion")
public class OcupacionesController {

	@Value("${innotec.com.elementosPorPagina}")
	String elementos ;
	
	@Autowired
	Empresa mieempresa ;
	
	@Autowired
	OcupacionImp ocupacionServImp;
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model modelo,
			HttpServletRequest request ) {
		
		int elemento = Integer.parseInt(this.elementos);
		Pageable pageRequest = PageRequest.of(page, elemento);
		//		
		HttpSession misession= request.getSession(true);		 
		mieempresa = (Empresa) misession.getAttribute("empresaCart");
		String mensaje  =   (String) misession.getAttribute("mensaje");
		//
		Page<Ocupacion> ocupacion = ocupacionServImp.findAllByEmpresa(mieempresa.getId(), pageRequest);//
		//
		PageRender<Ocupacion> pageRender = new PageRender<>("/inventario/listar", ocupacion, elemento);
		//
		modelo.addAttribute("mensaje", mensaje);	
		modelo.addAttribute("titulo", "Listado de Ocupaciones");
		  
		//
		modelo.addAttribute("empresatipos", mieempresa);
		modelo.addAttribute("page", pageRender);
		modelo.addAttribute("datos", ocupacion);
		//
		return "ocupacion/listar";
	}
	
	@RequestMapping(value="/ver/{id}")
	public String ver(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Ocupacion ocupacion = ocupacionServImp.findById(id);
		if (ocupacion==null) {
			flash.addAttribute("error", "La ocupación no existe en la Base de datos");
			return "redirect:/ocupacion/listar";
		}
		//
		
		model.put("titulo", "DETALLE DE LA OCUPACION Cliente: "+ocupacion.getReserva().getCliente().getNombredui());
		model.put("datos",ocupacion);
		//
		return "ocupacion/ver";
	}
	
}
