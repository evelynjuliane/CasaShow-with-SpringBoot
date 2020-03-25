package com.mvccasa.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mvccasa.model.Casa;
import com.mvccasa.services.CasaServices;

@Controller
@RequestMapping("/casas")
public class CasaController {

	@Autowired
	private CasaServices services;
	
    
	@RequestMapping
	public ModelAndView index(Long id) {
    	
		Optional<Casa> casas  = services.search(id); 
		
		ModelAndView mv = new ModelAndView("Casas");
		
		mv.addObject("casas", casas);
		return mv;

	}
	
	@RequestMapping("/create")
    public ModelAndView create() {
    	ModelAndView mv = new ModelAndView("CreateCasa");
    	mv.addObject(new Casa());
        return mv;
    }
	
	//SAVE
    @RequestMapping(method = RequestMethod.POST)
    public String save(@Validated Casa casa, Errors errors, RedirectAttributes attributes) {
    	
    	if(errors.hasErrors()) {
    		return "CreateCasa";
    	}
    	try {
    		services.save(casa);
    		attributes.addFlashAttribute("menssage", "Título salvo com sucesso!");
    		return "redirect:/casas/create";
		} catch (IllegalArgumentException e) {
			return "CreateCasa";
		}
	    
    	
    }
}
