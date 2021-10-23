package com.earthbook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.earthbook.models.Libro;
import com.earthbook.repository.IAutorRepository;
import com.earthbook.repository.ICategoriaRepository;
import com.earthbook.repository.IEditorialRepository;
import com.earthbook.repository.ILibroRepository;

@Controller()
public class LibroController {
    
    @Autowired
    private ILibroRepository repoLibro;
    
    @Autowired
    private IEditorialRepository repoEditorial;
    
    @Autowired
    private IAutorRepository repoAutor;
    
    @Autowired
    private ICategoriaRepository repoCat;
    
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

	    model.addAttribute("titulo", "Registrar Libro");
	    model.addAttribute("libro", new Libro());
	    
	    model.addAttribute("lstCategoria", repoCat.findAll());
	    model.addAttribute("lstEditoriales", repoEditorial.findAll());
	    model.addAttribute("lstAutores", repoAutor.findAll());
	    
	    model.addAttribute("accionBtn", "Registrar");
	    return "crudlibro";
	}
	
	@PostMapping("libro/guardar")
	public String guardar(@ModelAttribute Libro libro, Model model) {
	    
	    libro.setSKU(libro.getISBN());
	    libro.setUrlImagen("FALTA_URL");
	    
	    System.out.println(libro);
	    
	    model.addAttribute("lstCategoria", repoCat.findAll());
        model.addAttribute("lstEditoriales", repoEditorial.findAll());
        model.addAttribute("lstAutores", repoAutor.findAll());
	    
	    model.addAttribute("accionBtn", "Registrar");
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


