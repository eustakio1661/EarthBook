package com.earthbook.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	
	@GetMapping({"SignIn"})
	public String login(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session != null) {
			return "index";
		}
			model.addAttribute("titulo", "Login");
			model.addAttribute("usuario", new Usuario());
			return "login";
			
	}
	
	@PostMapping({"validar"})
	public String validate(@ModelAttribute Usuario usuario, Model model, HttpSession session ) {
		Usuario u = repositoryUsr.findByCorreoAndClave(usuario.getCorreo(), usuario.getClave());
		System.out.println(u);
		if(u != null) {
			session.setAttribute("usuario", u);	
			System.out.println(session.toString());
			return "index";
		}else {			
			model.addAttribute("usuario", new Usuario());
			model.addAttribute("loginUsuario", "correo o clave incorrecto...!!!");
			return "login";
		}
	}
	
	@GetMapping({"SignUp"})
	public String registro(Model model) {
		model.addAttribute("titulo", "Registro");
		model.addAttribute("usuario",new Usuario());
		return "registro";
	}
	
	@PostMapping({"grabarUsuario"})
	public String procesoGrabar(@ModelAttribute Usuario usuario, Model model, HttpSession session) {
		if(usuario!=null) {
			usuario.setImg("https://res.cloudinary.com/dfuuywyk9/image/upload/v1621437436/l60Hf_megote.png");
			usuario.setRol(2);
			usuario.setEstado(1);
			repositoryUsr.save(usuario);
			session.setAttribute("usuario", new Usuario());
			model.addAttribute("registroUsuario", "Usuario registrado con éxito...!!!");
			return "registro";
		}else {			
			return "registro";
		}		
	}		
	
	@GetMapping({"/login"})
	public String procesoLogout(Model model, HttpSession session) {
		session.removeAttribute("usuario");
		System.out.println(session);
		model.addAttribute("titulo", "Login");
		return "login";
	}
}