package com.earthbook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibroController {
	@GetMapping({"home", "", "/"})
	public String home(Model model) {
		model.addAttribute("titulo", "Home");
		return "index";
	}
	
	@GetMapping("/Categorias")
	public String categorias(Model model) {
		model.addAttribute("titulo", "Categorias");
		return "Categorias";
	}
	
	
	
}


