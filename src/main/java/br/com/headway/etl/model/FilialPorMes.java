package br.com.headway.etl.model;

import java.io.Serializable;


public class FilialPorMes implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Double valor;
	private String mes;
	
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	
	
	
}
