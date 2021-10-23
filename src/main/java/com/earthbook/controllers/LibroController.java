package com.earthbook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller()
public class LibroController {
	@GetMapping({"home", "", "/"})
	public String home(Model model) {
		model.addAttribute("titulo", "Home");
		return "index";
	}
	
	@GetMapping("categorias")
	public String categorias(Model model) {
		model.addAttribute("titulo", "Categorias");
		return "Categorias";
	}
	
	
	@GetMapping("libro/registrar")
	public String registrar(Model model) {
	    model.addAttribute("titulo", "Registrar");
	    return "crudlibro";
	}
	
	@GetMapping("libro/listado")
    public String listado(Model model) {
        model.addAttribute("titulo", "Listado de libros");
        return "listadolibros";
    }
	
	@GetMapping("login")
	public String login(Model model) {
		model.addAttribute("titulo", "Login");
		return "login";
	}
	
	@GetMapping("registro")
	public String registro(Model model) {
		model.addAttribute("titulo", "Registro");
		return "registro";
	}
	
	@GetMapping("carrito")
	public String carrito(Model model) {
		model.addAttribute("titulo", "Carrito");
		return "carrito";
	}

}


