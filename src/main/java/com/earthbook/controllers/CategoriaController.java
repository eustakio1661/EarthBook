package com.earthbook.controllers;

import com.earthbook.models.Categoria;
import com.earthbook.repository.ICategoriaRepository;
import com.earthbook.repository.ILibroRepository;

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

@Controller()
@SessionAttributes("categoria")
public class CategoriaController {
	
	@Autowired
	private ICategoriaRepository repoCat;
	
	@Autowired
    private ILibroRepository repoLibro;
	
	@GetMapping("categoria/registrar")
	public String registrar(Model model) {
	    model.addAttribute("titulo", "Registrar");
	    model.addAttribute("categoria", new Categoria());
	    
	    model.addAttribute("accionBtn", "Registrar");
	    return "crudcategoria";
	}
	
	@PostMapping("categoria/grabar")
	public String grabarPag(@ModelAttribute Categoria categoria, Model model, SessionStatus status) {
		
		 model.addAttribute("tipoMensaje", "success");
		 
		System.out.println("Listo para grabar");
		System.out.println(categoria.getId());
		
		if(categoria.getId() != 0) {
            model.addAttribute("accionBtn", "Actualizar");
            model.addAttribute("mensaje", "Categoria actualizado correctamente");
            
        } else {
            model.addAttribute("accionBtn", "Registrar");
            model.addAttribute("mensaje", "Categoria registrado correctamente");
            model.addAttribute("categoria", new Categoria());
        }
		
		
		categoria.setUrlImagen("https://res.cloudinary.com/dfuuywyk9/image/upload/v1621437436/l60Hf_megote.png");
		categoria.setIdEstado(1);
		
		repoCat.save(categoria);    
		return "crudcategoria";
	}
	
	
	@GetMapping("categoria/listado")
    public String listadoCategoria(Model model) {
		model.addAttribute("lstCategorias", repoCat.findAllActive());
        return "listadocategoria";
    }
	
	
	@GetMapping("categoria/editar/{id}")
	public String editar(@PathVariable(value="id") String id, Model model) {
		
		if(!id.matches("[01-9]+")) return "redirect:/categoria/listado";
		
		Categoria categoria = repoCat.findById(Integer.parseInt(id)).orElse(null); 
		
		if(categoria == null) return "redirect:/categoria/listado";

		model.addAttribute("titulo", "Editar Categoria");
		
		model.addAttribute("categoria", categoria);
		
		model.addAttribute("accionBtn", "Actualizar");
		
		return "crudcategoria";
	}
	
	@GetMapping("categoria/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") String id, Model model) {
	    
	    if(!id.matches("[01-9]+")) return "redirect:/categoria/listado";
	    
	    Categoria categoria = repoCat.findById(Integer.parseInt(id)).orElse(null);
	    categoria.setIdEstado(2);
	    
	    repoCat.save(categoria);
	    	    
        return "redirect:/categoria/listado";
	}
	
	
	
	@GetMapping("consultaCatLibro/{id}")
	public String consultaCatLibro(@PathVariable(value="id") String id, Model model) {
		
		if(!id.matches("[0-9]+")) return "redirect:/categorias";
		
		model.addAttribute("lstLibros", repoLibro.findByIdCategoria(Integer.parseInt(id)));
		model.addAttribute("lstCategorias", repoCat.findAll());
		System.out.println();
		return "catalogo";
	}
	
	
	/*@GetMapping("buscarCat")
	public String consultaCat(@RequestParam(name="id") String id, Model model) {
		
		if(!id.matches("[1-9]+")) return "redirect:/catalogo";
		
		model.addAttribute("lstCategorias", repoCat.findById(Integer.parseInt(id)));
		model.addAttribute("lstCategorias", repoCat.findAll());
		return "Categorias";
	}*/

}
