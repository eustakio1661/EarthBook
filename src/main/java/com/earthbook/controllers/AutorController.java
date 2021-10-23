package com.earthbook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.earthbook.models.Autor;
import com.earthbook.repository.IAutorRepository;

@Controller()
public class AutorController {
	
	private IAutorRepository repoAut;

	@GetMapping("autor/registrar")
	public String registrar(Model model) {
	    model.addAttribute("titulo", "Registrar");
	    return "crudautor";
	}
	
	@PostMapping("autor/grabar")
	public String grabarPag(@ModelAttribute Autor autor, Model model) {
		System.out.println("Listo para grabar");
		System.out.println(autor);
		repoAut.save(autor);    
		return "crudautor";
	}
	
	@GetMapping("autor/listado")
    public String listadoAutor(Model model) {
        model.addAttribute("titulo", "Listado de libros");
        return "listadoautores";
    }

}

