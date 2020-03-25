package com.mvccasa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mvccasa.model.Casa;
import com.mvccasa.repository.Casas;

@Service
public class CasaServices {

	@Autowired
	private Casas casas;
	

	public Optional<Casa> search(Long id) {
		
		return casas.findById(id);
	}
	
	public void save(Casa casa) {
		casas.save(casa);
			
	}
}