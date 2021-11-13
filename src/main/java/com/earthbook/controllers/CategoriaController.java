package com.earthbook.controllers;

import com.earthbook.models.Categoria;

import com.earthbook.repository.ICategoriaRepository;
import com.earthbook.repository.ILibroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller()
public class CategoriaController {
	
	@Autowired
	private ICategoriaRepository repoCat;
	
	@Autowired
    private ILibroRepository repoLibro;
	
	@GetMapping("categoria/registrar")
	public String registrar(Model model) {
	    model.addAttribute("titulo", "Registrar");
	    model.addAttribute("categoria", new Categoria());
	    return "crudcategoria";
	}
	
	@PostMapping("categoria/grabar")
	public String grabarPag(@ModelAttribute Categoria categoria, Model model) {
		System.out.println("Listo para grabar");
		System.out.println(categoria);
		categoria.setUrlImagen("https://res.cloudinary.com/dfuuywyk9/image/upload/v1621437436/l60Hf_megote.png");
		repoCat.save(categoria);    
		return "crudcategoria";
	}
	
	
	@GetMapping("categoria/listado")
    public String listadoCategoria(Model model) {
		model.addAttribute("lstCategorias", repoCat.findAll());
        return "listadocategoria";
    }
	
	
	@PostMapping("categoria/editar")
	public String buscarCat(@ModelAttribute Categoria c, Model model) {
		System.out.println();
		model.addAttribute("categoria", repoCat.findById(c.getId()));
		return "crudcategoria";
	}
	
	
	
	
	
	
	@PostMapping("ConsultaCatLibro")
	public String consultaLibro(@RequestParam(name="idCategoria") int idCategoria, Model model) {
		model.addAttribute("lstLibros", repoLibro.findByIdCategoria(idCategoria));
		model.addAttribute("lstCategorias", repoCat.findAll());
		return "catalogo";
	}
	
	

}
