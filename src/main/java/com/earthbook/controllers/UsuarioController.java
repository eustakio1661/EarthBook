package com.earthbook.controllers;

import javax.validation.constraints.AssertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.earthbook.models.Usuario;
import com.earthbook.repository.IUsuarioRepository;

@Controller
public class UsuarioController {
	
	@Autowired
	private IUsuarioRepository repositoryUsr;
	
	@GetMapping({"/login"})
	public String login(Model model) {
		model.addAttribute("titulo", "Login");
		return "login";
	}
	
	@PostMapping({"/validar"})
	public String validate(@ModelAttribute Usuario usuario, Model model) {
		Usuario u = repositoryUsr.findByCorreoAndClave(usuario.getCorreo(), usuario.getClave());
		if(u == null) {
			model.addAttribute("usuario", new Usuario());
			model.addAttribute("mensaje", "correo o clave incorrecto...!!!");
			return "login";
		
		}else {
			model.addAttribute("usuario", u);		
			return "index";
		}
	}
	
	@GetMapping({"/registro"})
	public String registro(Model model) {
		model.addAttribute("titulo", "Registro");
		return "registro";
	}
	
	@PostMapping({"/grabarRegistro"})
	public String procesoGrabar(@ModelAttribute Usuario usuario, Model model) {
		/*Usuario u = new Usuario();
		u.setNombre(usuario.getNombre());
		u.setApellido(usuario.getApellido());
		u.setTelefono(usuario.getTelefono());
		u.setCorreo(usuario.getCorreo());
		u.setClave(encoder.encode(usuario.getClave()));*/
		return "login";
	}	
	
}