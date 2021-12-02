package com.earthbook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.earthbook.models.Autor;
import com.earthbook.models.Categoria;
import com.earthbook.models.Editorial;
import com.earthbook.models.Libro;
import com.earthbook.models.Usuario;
import com.earthbook.repository.IAutorRepository;
import com.earthbook.repository.ICategoriaRepository;
import com.earthbook.repository.IEditorialRepository;
import com.earthbook.repository.ILibroRepository;
import com.earthbook.repository.IUsuarioRepository;

@Controller()
@SessionAttributes("libro")
public class LibroController {
    
    @Autowired
    private ILibroRepository repoLibro;
    @Autowired
	private IAutorRepository repoAut;
    @Autowired
    private IEditorialRepository repoEditorial;
    
    @Autowired
    private IAutorRepository repoAutor;
    
    @Autowired
    private ICategoriaRepository repoCat;
	@Autowired
	private IUsuarioRepository repositoryUsr;

    
	@GetMapping({"home", "", "/"})
	public String home(Model model) {
		model.addAttribute("titulo", "Home");
		return "index";
	}
	@GetMapping({"catalogo"})
	public String catalogo(Model model) {
		model.addAttribute("titulo", "Catalogo");
		model.addAttribute("lstCategorias", repoCat.findAll());
		model.addAttribute("lstLibros", repoLibro.findAllActive());
		model.addAttribute("lstEditoriales", repoEditorial.findAllActive());
		model.addAttribute("lstAutores", repoAut.findAllActive());
		return "catalogo";
	}
	

	@GetMapping({"dashboard"})
	public String dashboard(Model model) {

		return "dashboard";
	}
	
	
	@GetMapping("catalogo/verLibro/{id}")
	public String verlibro(@PathVariable(value="id") String id, Model model) {
		
		if(!id.matches("[01-9]+")) return "redirect:/catalogo";
		
		Libro libro = repoLibro.findById(Integer.parseInt(id)).orElse(null); 
		
		if(libro == null) return "redirect:/catalogo";

		List<Autor> listaAutor = repoAut.findAllActive();
		for (Autor a : listaAutor) {
			if(a.getId() == libro.getIdAutor()) {
				
				 model.addAttribute("nombreAutor",a.getNombre());
			}
		}
		
		List<Editorial> listaEditorial = repoEditorial.findAllActive();
		for (Editorial e : listaEditorial) {
			if(e.getId() == libro.getIdEditorial()) {
				
				 model.addAttribute("nombreEditorial",e.getDescripcion());
			}
		}
		
		List<Categoria> listaCategoria = repoCat.findAll();
		for (Categoria c : listaCategoria) {
			if(c.getId() == libro.getIdCategoria()) {		
				 model.addAttribute("nombreCategoria",c.getNombrecat());
			}
		}
		
		model.addAttribute("titulo", "Ver libro");
		model.addAttribute("libro", libro);
		return "verlibro";
		
	}
	
	
	@GetMapping("catalogoAutor")
	public String consultaLibroAutor(@RequestParam(name="idAutor") String idAutor, Model model) {
		
		if(!idAutor.matches("[1-9]+")) return "redirect:/catalogo";
		
		model.addAttribute("lstLibros", repoLibro.findByIdAutor(Integer.parseInt(idAutor)));
		model.addAttribute("lstCategorias", repoCat.findAllActive());
		model.addAttribute("lstEditoriales", repoEditorial.findAllActive());
		model.addAttribute("lstAutores", repoAut.findAllActive());
		return "catalogo";
	}
	
	@GetMapping("catalogoEditorial")
	public String consultaLibroEditorial(@RequestParam(name="idEditorial") String idEditorial, Model model) {
		
		if(!idEditorial.matches("[1-9]+")) return "redirect:/catalogo";
		
		model.addAttribute("lstLibros", repoLibro.findByIdEditorial(Integer.parseInt(idEditorial)));
		model.addAttribute("lstCategorias", repoCat.findAllActive());
		model.addAttribute("lstEditoriales", repoEditorial.findAllActive());
		model.addAttribute("lstAutores", repoAut.findAllActive());
		return "catalogo";
	}
	
	

	@GetMapping("buscarLibCat")
	public String consultaLibCat(@RequestParam(name="idCategoria") String idCategoria, Model model) {
		
		if(!idCategoria.matches("[1-9]+")) return "redirect:/catalogo";
		
		model.addAttribute("lstLibros", repoLibro.findByIdCategoria(Integer.parseInt(idCategoria)));
		model.addAttribute("lstCategorias", repoCat.findAllActive());
		model.addAttribute("lstEditoriales", repoEditorial.findAllActive());
		model.addAttribute("lstAutores", repoAut.findAllActive());
		return "catalogo";
	}
	
	@GetMapping("categorias")
	public String listadoCategoria(Model model) {
		model.addAttribute("lstCategorias", repoCat.findAllActive());
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
        model.addAttribute("lstLibros", repoLibro.findAllActive());
        return "listadolibros";
    }
	
	@GetMapping("libro/editar/{id}")
    public String editar(@PathVariable(value="id") String id, Model model) {	    
	    
        if(!id.matches("[01-9]+")) return "redirect:/libro/listado";

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
	

	@GetMapping("libro/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") String id, Model model) {
	    
	    if(!id.matches("[01-9]+")) return "redirect:/libro/listado";
	    
	    Libro libro = repoLibro.findById(Integer.parseInt(id)).orElse(null);
	    libro.setIdEstado(2);
	    
	    repoLibro.save(libro);
	    
	    model.addAttribute("lstCategoria", repoCat.findAll());
        model.addAttribute("lstEditoriales", repoEditorial.findAll());
        model.addAttribute("lstAutores", repoAutor.findAll());
	    
        return "redirect:/libro/listado";
	}
	

	
	@GetMapping("carrito")
	public String carrito(Model model) {
		model.addAttribute("titulo", "Carrito");
		return "carrito";
	}
	
	

	
}


