package innotech.com.sv.controladores;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import innotech.com.sv.ProcesosServices.Miscelaneos;
import innotech.com.sv.ProcesosServices.ReservaImp;
import innotech.com.sv.modelos.Empresa;
import innotech.com.sv.modelos.Promocion;
import innotech.com.sv.modelos.Reserva;
import innotech.com.sv.paginator.PageRender;
import innotech.com.sv.servicios.EmpresaServiceImp;
import innotech.com.sv.servicios.PromocionImp;

@Controller
@SessionAttributes({"reserva","empresa"})
@RequestMapping("/reserva")
public class ReservaController {
protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	PromocionImp promocionServ;
	
	@Value("${innotec.com.elementosPorPagina}")
	String elementos ;
	
	@Autowired
	EmpresaServiceImp empresaServ;
	
	@Autowired
	Empresa mieempresa ;
	
	@Autowired
	ReservaImp reservaServimp;
	
	
	@RequestMapping(value="/listar", method = RequestMethod.GET)
	public String inicial (@RequestParam(name="page", defaultValue="0") int page,   Model modelo, 			  
			   HttpServletRequest request ) {
		
		int elemento = Integer.parseInt(this.elementos);  
				
		HttpSession misession= request.getSession(true);		 
		mieempresa = (Empresa) misession.getAttribute("empresaCart");
				 
		Pageable  pageRequest =  PageRequest.of(page, elemento);
					
		Page<Reserva> reserva = reservaServimp.findAllByEmpresa(mieempresa, pageRequest)  ;
		
		PageRender<Reserva> pageRender = new PageRender<>("/reserva/listar", reserva, elemento) ;
		 
		System.out.println(mieempresa.getNombre());
		 
	     String mensaje  =   (String) misession.getAttribute("mensaje");
	     	  
	     modelo.addAttribute("mensaje", mensaje);
	     	
		modelo.addAttribute("titulo","Mantenimiento de Reservas");	
		modelo.addAttribute("datos",reserva);
		modelo.addAttribute("empresa",mieempresa);
		modelo.addAttribute("page",pageRender);
		return "reserva/listar";
	};
	
	
	@RequestMapping(value="/ver/{id}")
	public String ver(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Reserva reserva = reservaServimp.findById(id);
		if (reserva==null) {
			flash.addAttribute("error", "La reserva no existe en la Base de datos");
			return "redirect:/reserva/listar";
		}
		//
		model.put("reserva", reserva);
		model.put("titulo", "Detalle Reserva: "+reserva.getId());
		model.put("datos",reserva);
		//
		return "reserva/ver";
	}
	
	@RequestMapping(value="/procesar/{id}")
	public String procesar (@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {	
	    //
		if(id > 0) {
			Reserva reserva = reservaServimp.findById(id);
			if (reserva == null) {
				flash.addFlashAttribute("error", " El Id de la reserva no existe en la Base de datos");
				return "redirect:/reserva/listar";}
			
			 //
			 Date fechaini = Miscelaneos.ParseFecha("2021-03-02");
			 Date fechaFin = Miscelaneos.ParseFecha("2021-03-05");
			 
			 long empresa    = 1;
			 long habitacion = 1;
			 long reservas   = 1;
			 
			 System.out.println("Antes de la reserva");
			 
			 reservaServimp.reservar(reservas, empresa, habitacion, fechaini, fechaFin);		
			 
			
			/* try {
				 reservaServimp.reservar(reservas, empresa, habitacion, fechaini, fechaFin);				 
				 System.out.println("Luego de la reserva");
			} catch (Exception e) {
				flash.addFlashAttribute("error", " Error al guardar la reserva en la BD " + e.toString());
				return "redirect:reserva/listar";
			}*/
			
			
		} else {
			flash.addFlashAttribute("error", " Id de Reserva no existe");
			return "redirect:/reserva/listar";
		}
		
		return "redirect:/reserva/listar";
	};
	
	
	@RequestMapping(value="/form") 
	public String form (Model modelo) {	
		Reserva reserva = new Reserva();
		//---
		modelo.addAttribute("titulo","Creación de Promociones");	
		modelo.addAttribute("reserva",reserva);
		
		return "reserva/form";
	};
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String salvar (@Valid @ModelAttribute(value="promocion") Promocion promocion, BindingResult result, Model model, 
			RedirectAttributes flash, SessionStatus status) {	
		
		if (result.hasErrors()) {
			model.addAttribute("titulo","Creación de Promociones");						
			return "promocion/form";
		} else {
			String mensajeFlash =  ( String.valueOf(promocion.getId()) != null)? "Promoción Editada con éxito" : " Promoción guardada con éxito "  ;
		     promocionServ.save(promocion);
			model.addAttribute("titulo","Creación de Promociones");
		    status.setComplete();
		    flash.addFlashAttribute("success", mensajeFlash );
		
		return "redirect:/promocion/listar";
		}
	};
	
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Promocion promocion = null;
		
		if(id > 0) {
			promocion = promocionServ.findById(id);
			if (promocion == null) {
				flash.addFlashAttribute("error", " La promoción no existe en la Base de datos");
				return "redirect:promocion/listar";
			}
		} else {
			flash.addFlashAttribute("error", " Promoción no existe");
			return "redirect:/promocion/listar";
		}
		
		
		model.put("promocion", promocion);
		model.put("titulo", "Editar Promocion");
		flash.addFlashAttribute("success", " Promocion guardada con éxito");
		return "promocion/form";
	}
	
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		if(id > 0) {
			try {
				reservaServimp.delete(id);
				flash.addFlashAttribute("success", " Reservacion eliminada con éxito");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("error al borrar " +e.getMessage());
				flash.addFlashAttribute("error", " Error al intentar eliminar la reserva "+e.getMessage());
			}
			
		}		
		return "redirect:/reserva/listar";
	}
	
}