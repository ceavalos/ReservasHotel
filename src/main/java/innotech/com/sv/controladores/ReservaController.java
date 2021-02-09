package innotech.com.sv.controladores;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.GetMapping;
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
import innotech.com.sv.modelos.EstadoReservasEnum;
import innotech.com.sv.modelos.Habitacion;
import innotech.com.sv.modelos.PeriodoReservaEnum;
import innotech.com.sv.modelos.Promocion;
import innotech.com.sv.modelos.Reserva;
import innotech.com.sv.modelos.TiposHabitacion;
import innotech.com.sv.paginator.PageRender;
import innotech.com.sv.servicios.EmpresaServiceImp;
import innotech.com.sv.servicios.HabitacionImp;
import innotech.com.sv.servicios.PromocionImp;
import innotech.com.sv.servicios.TipoHabitacionImp;

@Controller
@SessionAttributes({"reserva","empresa","promocion","tiposhabitaciones"})
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
	
	@Autowired
	HabitacionImp habitacionServImp;
	
	@Autowired
	TipoHabitacionImp tipoHabitacionServImp;
	
	@RequestMapping(value="/listar", method = RequestMethod.GET)
	public String inicial (@RequestParam(name="page", defaultValue="0") int page,   Model modelo, 			  
			   HttpServletRequest request ) {
		
		int elemento = Integer.parseInt(this.elementos);  
				
		HttpSession misession= request.getSession(true);		 
		mieempresa = (Empresa) misession.getAttribute("empresaCart");
				 
		Pageable  pageRequest =  PageRequest.of(page, elemento);
					
		Page<Reserva> reserva = reservaServimp.findAllByEmpresa(mieempresa, pageRequest)  ;
		
		List<Habitacion> habitacion = null;
		
		List<Promocion> promociones = promocionServ.findByEmpresa(mieempresa.getId());
		
		PageRender<Reserva> pageRender = new PageRender<>("/reserva/listar", reserva, elemento) ;
		 
		List<TiposHabitacion> tiposHabitacion = tipoHabitacionServImp.findByEmpresa(mieempresa);
		
		System.out.println(mieempresa.getNombre());
		 
	     String mensaje  =   (String) misession.getAttribute("mensaje");
	     	  
	     modelo.addAttribute("mensaje", mensaje);
	     	
		modelo.addAttribute("titulo","Mantenimiento de Reservas");	
		modelo.addAttribute("datos",reserva);
		modelo.addAttribute("empresa",mieempresa);
		modelo.addAttribute("page",pageRender);
		modelo.addAttribute("habitaciones",habitacion);
		modelo.addAttribute("promocion",promociones);	
		modelo.addAttribute("precio",120);	
		modelo.addAttribute("tiposhabitaciones",tiposHabitacion);		
		modelo.addAttribute("pendiente",EstadoReservasEnum.Pendiente);
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
	
	//@GetMapping(value="/ajax/habitaciones/{tipohabitacion}") 
	@RequestMapping(value="/ajaxhabita")
	public String ajaxBrands(@RequestParam("tipohabitacion") long tipo, Model modelo, HttpServletRequest request) {
		//long tipo = (long) 1;
		System.out.println("Mensaje desde /ajax/habitaciones");
		
		HttpSession misession= request.getSession(true);		 
		mieempresa = (Empresa) misession.getAttribute("empresaCart");
		
		TiposHabitacion tipohabitacion = tipoHabitacionServImp.findById(tipo); 
		
		List<Habitacion> habitacion = habitacionServImp.findAllByEmpresaAndTipohabitacion(mieempresa, tipohabitacion) ;
		
		for (Habitacion habita: habitacion) {
			System.out.println("Desde Controller --> "+habita.getTipohabitacion().getId());
		}
		
		modelo.addAttribute("habitaciones",habitacion);
		
		//return "redirect:reserva/form";
		return "reserva/form :: models";
		//return "redirect:/reserva/listar";
	}
	
	@RequestMapping(value="/ajaxprecio")
	public String ajaxPeriodoReserva(@RequestParam("tipohabitacion") long tipo, @RequestParam("periodoReserva") PeriodoReservaEnum periodo,  Model modelo, HttpServletRequest request) {
		//long tipo = (long) 1;
		System.out.println("Mensaje desde /ajax/ajaxPeriodoReserva periodo= " +periodo);
		
		HttpSession misession= request.getSession(true);		 
		mieempresa = (Empresa) misession.getAttribute("empresaCart");
		
		TiposHabitacion tipohabitacion = tipoHabitacionServImp.findById(tipo); 
		
		double precio;
		
		switch (periodo) {
		case Dia:
			precio = tipohabitacion.getPreciodia();
			break;
        case Semana :
        	precio = tipohabitacion.getPreciosemana();
			break;
        case Mes :
        	precio = tipohabitacion.getPreciomes();
			break;
		default:
			precio = 0;
			break;
		}
		//System.out.println(" tipohabitacion= " +tipohabitacion.getDescripcion() + " Precio= " + precio);
		
		modelo.addAttribute("precio",precio);
				
		return "reserva/form :: precio";
		
	}
	
	@RequestMapping(value="/procesar/{id}")
	public String procesar (@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {	
	    //
		if(id > 0) {
			Reserva reserva = reservaServimp.findById(id);
			if (reserva == null) {
				flash.addFlashAttribute("error", " El Id de la reserva no existe en la Base de datos");
				return "redirect:/reserva/listar";}
			
			if (reserva.getEstadoReserva() != EstadoReservasEnum.Pendiente) {
				flash.addFlashAttribute("error", " La reserva no esta en estado Pendiente y no se puede procesar");
				return "redirect:/reserva/listar";}
			
			 //
			 Date fechaini = Miscelaneos.ParseFecha("2021-03-05");
			 Date fechaFin = Miscelaneos.ParseFecha("2021-03-20");
			  
			 long empresa    = 1;
			 long habitacion = 1;
			 long reservas   = 1;
			 
			 System.out.println("Antes de la reserva");
	
			 String resp = reservaServimp.reservar(reservas, empresa, habitacion, fechaini, fechaFin);
			 
			 if (resp != null) {
				 flash.addFlashAttribute("error",resp);				 
			 } else {
				 reserva.setEstadoReserva(EstadoReservasEnum.Activa);
				 reservaServimp.save(reserva);
				 flash.addFlashAttribute("info","Reserva efectuada con éxito");
			 }
			
			
		} else {
			flash.addFlashAttribute("error", id + " Id de Reserva no existe");
			return "redirect:/reserva/listar";
		}
		
		return "redirect:/reserva/listar";
	};
	
	
	@RequestMapping(value="/form") 
	public String form (Model modelo) {	
		Reserva reserva = new Reserva();
		//---
		modelo.addAttribute("titulo","Creación de Reservas");	
		modelo.addAttribute("reserva",reserva);
		
		return "reserva/form";
	};
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String salvar (@Valid @ModelAttribute(value="reserva") Reserva reserva, BindingResult result, Model model, 
			RedirectAttributes flash, SessionStatus status) {	
		
		if (result.hasErrors()) {
			model.addAttribute("titulo","Creación de Reservas");						
			return "reserva/form";
		} else {
			String mensajeFlash =  ( String.valueOf(reserva.getId()) != null)? "Reserva Editada con éxito" : " Reserva guardada con éxito "  ;
			reservaServimp.save(reserva);
			model.addAttribute("titulo","Creación de Reservas");
		    status.setComplete();
		    flash.addFlashAttribute("success", mensajeFlash );
		
		return "redirect:/reserva/listar";
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
			return "redirect:/reserva/listar";
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