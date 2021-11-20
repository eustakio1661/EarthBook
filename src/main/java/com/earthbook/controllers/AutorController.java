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

import com.earthbook.models.Autor;
import com.earthbook.repository.IAutorRepository;

@Controller()
@SessionAttributes("autor")
public class AutorController {
	
	@Autowired
	private IAutorRepository repoAut;

	@GetMapping("autor/registrar")
	public String registrar(Model model) {
	    model.addAttribute("titulo", "Registrar");
	    model.addAttribute("autor", new Autor());
	    
	    model.addAttribute("accionBtn", "Registrar");
	    return "crudautor";
	}
	
	@PostMapping("autor/grabar")
	public String grabarPag(@ModelAttribute Autor autor, Model model, SessionStatus status) {
		
		model.addAttribute("tipoMensaje", "success");
		
		System.out.println("Listo para grabar");
		System.out.println(autor.getId());
		
		if(autor.getId() != 0) {
            model.addAttribute("accionBtn", "Actualizar");
            model.addAttribute("mensaje", "Autor actualizado correctamente");
            
        } else {
            model.addAttribute("accionBtn", "Registrar");
            model.addAttribute("mensaje", "Autor registrado correctamente");
            model.addAttribute("autor", new Autor());
        }
		
		
		autor.setUrlImagen("https://res.cloudinary.com/dfuuywyk9/image/upload/v1621437436/l60Hf_megote.png");
		autor.setIdEstado(1);
		
		repoAut.save(autor);    
		return "crudautor";
	}
	
	@GetMapping("autor/listado")
    public String listadoAutor(Model model) {
		model.addAttribute("titulo", "Listado de autores");
		model.addAttribute("lstAutores", repoAut.findAllActive());
        return "listadoautores";
    }
	
	@GetMapping("autor/editar/{id}")
	public String editar(@PathVariable(value="id") String id, Model model) {
		
		if(!id.matches("[01-9]+")) return "redirect:/autor/listado";
		
		Autor autor = repoAut.findById(Integer.parseInt(id)).orElse(null); 
		
		if(autor == null) return "redirect:/autor/listado";

		model.addAttribute("titulo", "Editar autor");
		model.addAttribute("autor", autor);
		model.addAttribute("accionBtn", "Actualizar");
		
		return "crudautor";
	}
	
	@GetMapping("autor/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") String id, Model model) {
	    
	    if(!id.matches("[01-9]+")) return "redirect:/autor/listado";
	    
	    Autor autor = repoAut.findById(Integer.parseInt(id)).orElse(null); 
	    autor.setIdEstado(2);
	    
	    repoAut.save(autor);    
	    
        return "redirect:/autor/listado";
	}
	
	

}

