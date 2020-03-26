package com.mvccasa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.mvccasa.model.Evento;
import com.mvccasa.repository.EventoFilter;
import com.mvccasa.repository.Eventos;

@Service
public class EventoServices {
	
	@Autowired
	private Eventos eventos;
	

	public Iterable<Evento> search(@ModelAttribute("filter") EventoFilter filter) {
		String nome = filter.getNome() == null ? "" : filter.getNome();
    	
		return eventos.findByNomeContaining(nome);
	}
	
	public void save(Evento evento) {
		try {
			eventos.save(evento);
		}catch(DataIntegrityViolationException e){
			throw new IllegalArgumentException("Formato de data inválido");
		}
		
	}

	public void delete(Evento evento) {
		eventos.delete(evento);
		
	}


	
}
