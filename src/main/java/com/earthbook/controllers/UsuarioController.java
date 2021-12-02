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

	@GetMapping({ "SignIn" })
	public String login(Model model) {
		model.addAttribute("titulo", "Login");
		model.addAttribute("usuario", new Usuario());
		return "login";

	}

	@PostMapping({ "validar" })
	public String validate(@ModelAttribute Usuario usuario, Model model) {
		Usuario u = repositoryUsr.findByCorreoAndClave(usuario.getCorreo(), usuario.getClave());
		
		if (u != null) {
			System.out.println(u.toString());
			model.addAttribute("usuario", u);
			model.addAttribute("usuarioExiste","NO");
			return "dashboard";
		} else {
			model.addAttribute("usuarioExiste","SI");
			model.addAttribute("usuario", new Usuario());
			model.addAttribute("loginUsuario", "correo o clave incorrecto...!!!");
			return "login";
		}
	}

	@GetMapping({"cerrarSesion" })
	public String cerrarSesion(Model model) {
		model.addAttribute("titulo", "Cerrar Sesion");
		model.addAttribute("usuario", new Usuario());
		return "login";

	}

	@GetMapping({ "SignUp" })
	public String registro(Model model) {
		model.addAttribute("titulo", "Registro");
		model.addAttribute("usuario", new Usuario());
		return "registro";
	}

	@PostMapping({ "grabarUsuario" })
	public String procesoGrabar(@ModelAttribute Usuario usuario, Model model) {
		if (usuario != null) {
			usuario.setImg("https://res.cloudinary.com/dfuuywyk9/image/upload/v1621437436/l60Hf_megote.png");
			usuario.setRol(2);
			usuario.setEstado(1);
			repositoryUsr.save(usuario);
			model.addAttribute("usuario", new Usuario());
			model.addAttribute("registroUsuario", "Usuario registrado con éxito...!!!");
			return "registro";
		} else {
			return "registro";
		}
	}

}