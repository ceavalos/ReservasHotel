package innotech.com.sv.seguridad;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import innotech.com.sv.Authentication.LoginSuccessHandler;
import innotech.com.sv.modelos.Empresa;
import innotech.com.sv.modelos.Usuarios;
import innotech.com.sv.modelos.UsuariosBycia;
import innotech.com.sv.modelosDao.UsuarioDao;
import innotech.com.sv.modelosDao.UsuariosByciaDao;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	private final static Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);
	
	@Autowired
	private UsuariosByciaDao usuarioBycia;
	
	@Autowired
	private UsuarioDao  usuarioDao;
	
	
	@Bean 
	public BCryptPasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }
	
	public void AddViewControllers( ViewControllerRegistry registry) {
		registry.addViewController("/error_403").setViewName("error_403");
	}
	
	
}
