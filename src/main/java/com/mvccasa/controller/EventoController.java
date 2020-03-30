package com.mvccasa.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mvccasa.model.Casa;
import com.mvccasa.model.Categorias;
import com.mvccasa.model.Evento;
import com.mvccasa.repository.Casas;
import com.mvccasa.repository.EventoFilter;
import com.mvccasa.services.EventoServices;

@Controller
@RequestMapping("/eventos")
public class EventoController {
	
	
	
	@Autowired
	private EventoServices services; 
	
	@Autowired 
	private Casas repository;
	
	@RequestMapping
	public ModelAndView index(@ModelAttribute("filter") EventoFilter filter) {
    	
		Iterable<Evento> eventos  = services.search(filter); 
		
		ModelAndView mv = new ModelAndView("/Evento/Eventos");
		
		mv.addObject("eventos", eventos);
		return mv;

	}
	@RequestMapping("/create")
    public ModelAndView create() {
    	ModelAndView mv = new ModelAndView("/Evento/CreateEvento");
    	mv.addObject(new Evento());
        return mv;
    }
	
	//SAVE
    @RequestMapping(method = RequestMethod.POST)
    public String save(@Validated Evento evento, Errors errors, RedirectAttributes attributes, @RequestParam("file") MultipartFile file ) {
    	
    	if(errors.hasErrors()) {
    		return "/Evento/CreateEvento";
    	}
    	try {
    		services.save(evento,attributes, file);
    		return "redirect:/eventos/create";
		} catch (IllegalArgumentException e) {
			return "/Evento/CreateEvento";
		}
    
    }
    
    @RequestMapping("{id}")
    public ModelAndView edit(@PathVariable("id") Evento evento) {
    	ModelAndView mv = new ModelAndView("/Evento/CreateEvento");
		mv.addObject(evento);
		return mv;
	}
    //DELETE
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Evento evento) {
    	services.delete(evento);
        return "redirect:/eventos";
    }
    
    @RequestMapping("/mostrar/{id}")
    @ResponseBody
    public byte[] mostrar(@PathVariable("img") String img) throws IOException {
    	System.out.println(services.mostrar(img));
    	return services.mostrar(img);
    }
   
    @ModelAttribute("allCasas")
    public List<Casa> allCasas(){
    	return repository.findAll();
    }
    
    @ModelAttribute("allCategorias")
    public List<Categorias> allCategorias(){
    	return Arrays.asList(Categorias.values());
    }
	
}
