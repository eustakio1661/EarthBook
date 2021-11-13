package com.earthbook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.earthbook.models.Libro;
import com.earthbook.repository.IAutorRepository;
import com.earthbook.repository.ICategoriaRepository;
import com.earthbook.repository.IEditorialRepository;
import com.earthbook.repository.ILibroRepository;

@Controller()
@SessionAttributes("libro")
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
	@GetMapping({"catalogo"})
	public String catalogo(Model model) {
		model.addAttribute("titulo", "Catalogo");
		return "catalogo";
	}
	
	@GetMapping("categorias")
	public String listadoCategoria(Model model) {
		model.addAttribute("lstCategorias", repoCat.findAll());
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
	public String guardar(@ModelAttribute Libro libro, Model model, SessionStatus status) {

	    model.addAttribute("lstCategoria", repoCat.findAll());
        model.addAttribute("lstEditoriales", repoEditorial.findAll());
        model.addAttribute("lstAutores", repoAutor.findAll());
	    
        model.addAttribute("tipoMensaje", "success");
        
        System.out.println("LIBRO ID: " + libro.getId());
        
        if(libro.getId() != 0) {
            model.addAttribute("accionBtn", "Actualizar");
            model.addAttribute("mensaje", "Libro actualizado correctamente");
            
        } else {
            model.addAttribute("accionBtn", "Registrar");
            model.addAttribute("mensaje", "Libro registrado correctamente");
            model.addAttribute("libro", new Libro());
        }
        
        libro.setSKU(libro.getISBN());
        libro.setUrlImagen("FALTA_URL");
        libro.setIdEstado(1);
        
        repoLibro.save(libro);
	    
	    return "crudlibro";
	}
	
	@GetMapping("libro/listado")
    public String listado(Model model) {
        model.addAttribute("titulo", "Listado de libros");
        model.addAttribute("lstLibros", repoLibro.findAll());
        return "listadolibros";
    }
	
	@GetMapping("libro/editar/{id}")
    public String editar(@PathVariable(value="id") String id, Model model) {	    
	    
        if(!id.matches("[1-9]+")) return "redirect:/libro/listado";

        Libro libro = repoLibro.findById(Integer.parseInt(id)).orElse(null);  

        if(libro == null) return "redirect:/libro/listado";
	    
        model.addAttribute("titulo", "Editar libro");
        
        model.addAttribute("lstCategoria", repoCat.findAll());
        model.addAttribute("lstEditoriales", repoEditorial.findAll());
        model.addAttribute("lstAutores", repoAutor.findAll());        
        model.addAttribute("libro", libro);
        
        model.addAttribute("accionBtn", "Actualizar");
        
        return "crudlibro";
    }
	
	@GetMapping("login")
	public String login(Model model) {
		model.addAttribute("titulo", "Login");
		return "login";
	}

	@GetMapping({"registro"})
	public String registro(Model model) {
		model.addAttribute("titulo", "Registro");
		return "registro";

	}
	
	@GetMapping("carrito")
	public String carrito(Model model) {
		model.addAttribute("titulo", "Carrito");
		return "carrito";
	}
	
	@GetMapping("contactos")
	public String contactos(Model model) {
		model.addAttribute("titulo", "Contacto");
		return "contactos";
	}

	@GetMapping({"verLibro"})
	public String verlibro(Model model) {
		model.addAttribute("titulo", "Ver Libro");
		return "verlibro";
	}
}


