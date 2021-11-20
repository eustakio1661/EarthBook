package com.earthbook.controllers;

import com.earthbook.models.Editorial;
import com.earthbook.repository.IEditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller()
@SessionAttributes("editorial")
public class EditorialController {
	
	@Autowired
	private IEditorialRepository repoEdit;

	
	@GetMapping("editorial/registrar")
	public String registrar(Model model) {
	    model.addAttribute("titulo", "Registrar");
	    model.addAttribute("editorial", new Editorial());
	    
	    model.addAttribute("accionBtn", "Registrar");
	    return "crudeditorial";
	}
	
	@PostMapping("editorial/grabar")
	public String grabarPag(@ModelAttribute Editorial editorial, Model model, SessionStatus status) {
		
		 model.addAttribute("tipoMensaje", "success");
		 
		System.out.println("Listo para grabar");
		System.out.println(editorial.getId());
		
		if(editorial.getId() != 0) {
            model.addAttribute("accionBtn", "Actualizar");
            model.addAttribute("mensaje", "Editorial actualizado correctamente");
            
        } else {
            model.addAttribute("accionBtn", "Registrar");
            model.addAttribute("mensaje", "Editorial registrado correctamente");
            model.addAttribute("editorial", new Editorial());
        }
		
		editorial.setIdEstado(1);
		
		repoEdit.save(editorial);    
		return "crudeditorial";
	}
	
	
	@GetMapping("editorial/listado")
    public String listadoCategoria(Model model) {
		model.addAttribute("lstEditoriales", repoEdit.findAllActive());
        return "listadoeditorial";
    }
	
	
	@GetMapping("editorial/editar/{id}")
	public String editar(@PathVariable(value="id") String id, Model model) {
		
		if(!id.matches("[01-9]+")) return "redirect:/categoria/listado";
		
		Editorial editorial = repoEdit.findById(Integer.parseInt(id)).orElse(null); 
		
		if(editorial == null) return "redirect:/editorial/listado";

		model.addAttribute("titulo", "Editar Editorial");
		
		model.addAttribute("editorial", editorial);
		
		model.addAttribute("accionBtn", "Actualizar");
		
		return "crudeditorial";
	}
	
	@GetMapping("editorial/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") String id, Model model) {
	    
	    if(!id.matches("[01-9]+")) return "redirect:/categoria/listado";
	    
	    Editorial editorial = repoEdit.findById(Integer.parseInt(id)).orElse(null);
	    editorial.setIdEstado(2);
	    
	    repoEdit.save(editorial);
	    	    
        return "redirect:/editorial/listado";
	}
	
}
