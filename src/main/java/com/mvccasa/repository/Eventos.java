package com.mvccasa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mvccasa.model.Evento;

public interface Eventos extends JpaRepository<Evento, Long> {

	
}
