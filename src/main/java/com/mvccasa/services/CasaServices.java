package com.mvccasa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.mvccasa.model.Casa;
import com.mvccasa.repository.CasaFilter;
import com.mvccasa.repository.Casas;

@Service
public class CasaServices {

	@Autowired
	private Casas casas;
	

	public Iterable<Casa> search(@ModelAttribute("filter") CasaFilter filter) {
		String nome = filter.getNome() == null ? "" : filter.getNome();
    	
		return casas.findByNomeContaining(nome);
	}
	
	public void save(Casa casa) {
		casas.save(casa);
			
	}
	
	public void delete(Casa casa) {
		this.casas.delete(casa);
			
	}
}