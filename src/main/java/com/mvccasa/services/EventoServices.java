package com.mvccasa.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mvccasa.model.Evento;
import com.mvccasa.repository.EventoFilter;
import com.mvccasa.repository.Eventos;

@Service
public class EventoServices {
	
	private static String path = "C://Users//EJDH//eclipse-workspace//MVCCASA//src//main//resources//static/img/";
	
	@Autowired
	private Eventos eventos;
	

	public Iterable<Evento> search(@ModelAttribute("filter") EventoFilter filter) {
		String nome = filter.getNome() == null ? "" : filter.getNome();
    	
		return eventos.findByNomeContaining(nome);
	}
	
	public void save(@Validated Evento evento, RedirectAttributes attributes, @RequestParam("file") MultipartFile file ) {
		
		try {
			eventos.saveAndFlush(evento);
			try {
				if(!file.isEmpty()) {
					byte[]  bytes = file.getBytes();
					Path img = Paths.get(path + String.valueOf(evento.getId()) + file.getOriginalFilename());
					Files.write(img, bytes);
					evento.setImg(String.valueOf(evento.getId()) + file.getOriginalFilename());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			eventos.save(evento);
			attributes.addFlashAttribute("menssage", "Evento salvo com sucesso!");
			
		}catch(DataIntegrityViolationException e){
			throw new IllegalArgumentException("Formato de data inválido");
		}
		
	}

	public void delete(Evento evento) {
		eventos.delete(evento);
		
	}

	//public void mostrar(String img) {
		//File imgFile = new File(path);
	//}
	
}
