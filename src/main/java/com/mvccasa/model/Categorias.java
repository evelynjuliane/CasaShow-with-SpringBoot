package com.mvccasa.model;

public enum Categorias {
	
	ROCK("Rock"), 
	SERTANEJO("Sertanejo"),
	FUNK("Funk"),
	PAGODE("Pagode"),
	POP("POP"),
	ELETRONICA("Eletronica");
	
	
	private String description;
	
	 Categorias(String description){
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
