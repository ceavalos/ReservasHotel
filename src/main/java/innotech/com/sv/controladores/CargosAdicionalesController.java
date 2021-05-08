package innotech.com.sv.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

import innotech.com.sv.ProcesosServices.ReservaImp;
import innotech.com.sv.modelos.CargosAdicionales;
import innotech.com.sv.modelos.ClaseServicio;
import innotech.com.sv.modelos.Empresa;
import innotech.com.sv.modelos.EstadoCargoAdicionalEnum;
import innotech.com.sv.modelos.Habitacion;
import innotech.com.sv.modelos.Ocupacion;
import innotech.com.sv.modelos.TiposHabitacion;
import innotech.com.sv.modelosDao.ClaseServicioDao;
import innotech.com.sv.paginator.PageRender;
import innotech.com.sv.servicios.ActivoImp;
import innotech.com.sv.servicios.CargosAdicionalesImp;
import innotech.com.sv.servicios.IOcupacion;
import innotech.com.sv.servicios.OcupacionImp;

@Controller
@SessionAttributes({"cargosadicionales","empresatipos","ocupaciones","reserva","habitacion","claseservicio"})
@RequestMapping("/cargosadicionales")
public class CargosAdicionalesController {
	
	@Value("${innotec.com.elementosPorPagina}")
	String elementos ;
	
	@Autowired
	ActivoImp inventarioImp;
	
	@Autowired
	Empresa mieempresa ;
	
	@Autowired
	CargosAdicionalesImp cargosAdicionalesimp;
	
	@Autowired
	IOcupacion ocupacionServImp;
	
	@Autowired
	ClaseServicioDao claseservImp;
	
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
		//List<CargosAdicionales> habitaciones = cargosAdicionalesimp.findByEmpresa(mieempresa);
		
		Page<CargosAdicionales> cargosadicionales = cargosAdicionalesimp.findByEmpresa(mieempresa, pageRequest);//   findAllByEmpresa(mieempresa, pageRequest);
		//
		
		PageRender<CargosAdicionales> pageRender = new PageRender<>("/cargosadicionales/listar", cargosadicionales, elemento);
		//		
		List<Ocupacion> Ocupacion = ocupacionServImp.findByEmpresa(mieempresa);
		//
		List<Habitacion> habitaciones = new ArrayList<Habitacion>();
		//
		for(Ocupacion hab: Ocupacion) {
			habitaciones.add(hab.getReserva().getHabitacion());
		}
		//
		List<ClaseServicio>  claseServicio = claseservImp.findByEmpresa(mieempresa);
		for(ClaseServicio temp :claseServicio ) {
			System.out.println(temp.getDescripcion());
		};
		//
		modelo.addAttribute("mensaje", mensaje);	
		modelo.addAttribute("titulo", "Listado de Cargos Adicionales");
		modelo.addAttribute("cargosadicionales", cargosadicionales);		
		modelo.addAttribute("empresatipos", mieempresa);		
		modelo.addAttribute("ocupaciones", Ocupacion);
		modelo.addAttribute("reserva", Ocupacion);
		modelo.addAttribute("page", pageRender);
		modelo.addAttribute("habitacion",habitaciones );
		modelo.addAttribute("claseservicio", claseServicio);
		
		return "cargosadicionales/listar";
	}
	
	//@GetMapping(value="/ajax/habitaciones/{tipohabitacion}") 
		@RequestMapping(value="/ajaxservicio")
		public String ajaxBrands(@RequestParam("tipohabitacion") long tipo, Model modelo, HttpServletRequest request) {
			//long tipo = (long) 1;
			System.out.println("Mensaje desde /ajax/habitaciones");
			
			HttpSession misession= request.getSession(true);		 
			mieempresa = (Empresa) misession.getAttribute("empresaCart");
			
		/*	TiposHabitacion tipohabitacion = tipoHabitacionServImp.findById(tipo); 
			
			List<Habitacion> habitacion = habitacionServImp.findAllByEmpresaAndTipohabitacion(mieempresa, tipohabitacion) ;
			
			for (Habitacion habita: habitacion) {
				System.out.println("Desde Controller --> "+habita.getTipohabitacion().getId());
			}
			
			modelo.addAttribute("habitaciones",habitacion);
			*/
			//return "redirect:reserva/form";
			return "cargosadicionales/form :: cargoservicio";
			//return "redirect:/reserva/listar";
		}
		
	
	@RequestMapping(value="/form") 
	public String form (Model modelo) {	
		CargosAdicionales cargos  = new CargosAdicionales();
		//---
		modelo.addAttribute("titulo","Creación de Cargos Adicionales");	
		modelo.addAttribute("cargosadicionales",cargos);
		//modelo.addAttribute("reserva", mieempresa);
		
		return "cargosadicionales/form";
	};
	
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String salvar (@Valid @ModelAttribute(value="cargosadicionales") CargosAdicionales cargosadicionales, BindingResult result, Model model, 
			RedirectAttributes flash, SessionStatus status) {	
		
		if (result.hasErrors()) {
			model.addAttribute("titulo","Creación de Activos");						
			return "cargosadicionales/form";
		} else {
			String mensajeFlash =  ( String.valueOf(cargosadicionales.getId()) != null)? "Cargos Adicionales Editados con éxito" : " Cargos Adicionales guardados con éxito "  ;
			cargosAdicionalesimp.save(cargosadicionales);
			model.addAttribute("titulo","Creación de Cargos Adicionales");
		    status.setComplete();
		    flash.addFlashAttribute("success", mensajeFlash );
		
		return "redirect:/cargosadicionales/listar";
		}
	};
	
	@RequestMapping(value="/crear/{id}")
	public String crear(@PathVariable(value="id") Long id, Map<String, Object> model, 
			            RedirectAttributes flash, Model modelo, HttpServletRequest request) {
				
		CargosAdicionales cargos  = new CargosAdicionales();
             //		
		HttpSession misession= request.getSession(true);		 
		mieempresa = (Empresa) misession.getAttribute("empresaCart");
		//
		Ocupacion ocupacion = ocupacionServImp.findById(id) ;
		
		cargos.setOcupacion(ocupacion);
		cargos.setEmpresa(mieempresa);
		cargos.setEstado(EstadoCargoAdicionalEnum.Pendiente);
		cargos.setRecurrente("N");
		System.out.println(cargos.getOcupacion().getReserva().getHabitacion().getCodigo());
		//---
		modelo.addAttribute("titulo","Creación de Cargos Adicionales");	
		modelo.addAttribute("cargosadicionales",cargos);
		//modelo.addAttribute("reserva", mieempresa);
		
		return "cargosadicionales/form";
				
	}

	
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		CargosAdicionales cargoadicional = null;
		
		if(id > 0) {
			cargoadicional = cargosAdicionalesimp.findById(id);
			if (cargoadicional == null) {
				flash.addFlashAttribute("error", " El codigo del Cargo Adicional no existe en la Base de datos");
				return "redirect:/cargosadicionales/listar";
			}
		} else {
			flash.addFlashAttribute("error", " Codigo de Activo no existe");
			return "redirect:/cargosadicionales/listar";
		}
		
		model.put("cargosadicionales", cargoadicional);
		model.put("titulo", "Editar Cargos Adicionales");
		
		flash.addFlashAttribute("success", " Cargo Adicional guardado con éxito");
		return "cargosadicionales/form";
	}
	
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			try {
				cargosAdicionalesimp.delete(id);
				flash.addFlashAttribute("success", " Cargo Adicional eliminado con éxito");
			} catch (Exception e) {
				System.out.println("error al borrar " +e.getMessage());
				flash.addFlashAttribute("error", " Error al intentar eliminar Cargo Adicional "+e.getMessage());
			}			
		}
		
		return "redirect:/cargosadicionales/listar";
	}
	

	@RequestMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		CargosAdicionales cargo = cargosAdicionalesimp.findById(id);		
		
		if (cargo == null) {
			flash.addAttribute("error", "El Cargo Adicional no existe en la Base de datos");
			return "redirect:/cargosadicionales/listar";
		}
		//
		model.put("titulo", "Cargos Adicionales ");
		model.put("cargosadicionales", cargo);
		//
		return "cargosadicionales/ver";
	}
	
}
