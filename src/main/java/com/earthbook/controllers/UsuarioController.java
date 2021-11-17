package com.earthbook.controllers;

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
		model.addAttribute("usuaio", new Usuario());
		return "login";
	}
	
	@PostMapping({"/validar"})
	public String validate(@ModelAttribute Usuario usuario, Model model) {
		System.out.println(usuario);
		Usuario u = repositoryUsr.findByCorreoAndClave(usuario.getCorreo(), usuario.getClave());
		System.out.println(u);
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
		model.addAttribute("usuario",new Usuario());
		return "registro";
	}
	
	@PostMapping({"/grabarRegistro"})
	public String procesoGrabar(@ModelAttribute Usuario usuario, Model model) {
		System.out.println(usuario);
		repositoryUsr.save(usuario);
		return "registro";
	}	
	
}