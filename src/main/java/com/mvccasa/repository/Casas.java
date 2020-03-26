package com.mvccasa.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.mvccasa.model.Casa;


public interface Casas extends JpaRepository<Casa, Long> {
	
	public Iterable<Casa> findByNomeContaining(String nome);
}
