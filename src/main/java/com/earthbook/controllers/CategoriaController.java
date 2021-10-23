package com.earthbook.controllers;

import com.earthbook.models.Categoria;

import com.earthbook.repository.ICategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller()
public class CategoriaController {
	
	@Autowired
	private ICategoriaRepository repoCat;
	
	@GetMapping("categoria/registrar")
	public String registrar(Model model) {
	    model.addAttribute("titulo", "Registrar");
	    return "crudcategoria";
	}
	
	@PostMapping("categoria/grabar")
	public String grabarPag(@ModelAttribute Categoria categoria, Model model) {
		System.out.println("Listo para grabar");
		System.out.println(categoria);
		repoCat.save(categoria);    
		return "crudcategoria";
	}
	
	
	@GetMapping("categoria/listado")
    public String listadoCategoria(Model model) {
        model.addAttribute("titulo", "Listado de Categorias");
        return "listadoCategoria";
    }

}
