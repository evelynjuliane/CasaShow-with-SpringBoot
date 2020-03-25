package com.mvccasa.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Error: Nome é um campo obrigatório!")
	private String nome;
	
	@ManyToOne
	private Casa casa;
	
	@Enumerated(EnumType.STRING)
	private Categorias categoria;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@DateTimeFormat(pattern = "hh:mm")
	@Temporal(TemporalType.TIME)
	private Date hora;

	@NotNull(message= "Error: Quantidade de Ingressos é um campo obrigatório!")
	@DecimalMin(value = "1")
	private int qtdIngresso;
	
	@NotNull(message= "Error: Valor é um campo obrigatório!")
	@DecimalMin(value = "0.01" , message = "Error: Valor não pode ser menor que 'R$0,01' !")
	@DecimalMax(value = "9999999.99", message = "Error: Valor não pode ser maior que 'R$9.999.999,99' !")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorIngresso;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Casa getCasa() {
		return casa;
	}

	public void setCasa(Casa casa) {
		this.casa = casa;
	}

	public Categorias getCategoria() {
		return categoria;
	}

	public void setCategoria(Categorias categoria) {
		this.categoria = categoria;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public int getQtdIngresso() {
		return qtdIngresso;
	}

	public void setQtdIngresso(int qtdIngresso) {
		this.qtdIngresso = qtdIngresso;
	}

	public BigDecimal getValorIngresso() {
		return valorIngresso;
	}

	public void setValorIngresso(BigDecimal valorIngresso) {
		this.valorIngresso = valorIngresso;
	}
	
	
	
	 
}
