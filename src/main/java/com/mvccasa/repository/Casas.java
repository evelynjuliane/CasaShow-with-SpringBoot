package com.mvccasa.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mvccasa.model.Casa;


public interface Casas extends JpaRepository<Casa, Long> {
	
	public Optional<Casa> findById(Long id);
}
