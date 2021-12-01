package com.earthbook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.earthbook.models.Contactos;
import com.earthbook.repository.IContactosRepository;

@Controller
public class ContactosController {
	
	@Autowired
	private IContactosRepository repoContacto;
	
	@GetMapping("contactos")
	public String cargar(Model model) {
		model.addAttribute("envContacto", new Contactos());
		return "contactos";
	}
	
	@PostMapping("/envio")
	public String grabar(@ModelAttribute Contactos contactos, Model model) {
		if(contactos == null) {
			return "contactos";
		
		}else {
			repoContacto.save(contactos); 
			model.addAttribute("envContacto", new Contactos());
			model.addAttribute("mensaje", "Se realizo su envio");
			return "contactos";
		}

}

}
