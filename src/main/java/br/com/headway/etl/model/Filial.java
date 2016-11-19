package br.com.headway.etl.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = "Filial.findAll", query = "select u from Filial u"),
	@NamedQuery(name = "Filial.filialMaisVendeu", query = "select f from Filial f order by f.mes, f.valor"),
	@NamedQuery(name = "Filial.filialMaiorCrescimento", query = "select f.filial, f.mes, max(f.valor) as valor from Filial f group by f.filial, f.mes"),
	@NamedQuery(name = "Filial.filialMaiorQueda", query = "select f.filial, f.mes, min(f.valor) as valor from Filial f group by f.filial, f.mes")})
public class Filial implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id_filial")
	private Long idFilial;
	@Column(length = 80)
	private String filial;
	@Column(length = 40)
	private String mes;
	@Column(precision = 2, scale = 2)
	private Double valor;	
	

	public Long getIdFilial() {
		return idFilial;
	}

	public void setIdFilial(Long idFilial) {
		this.idFilial = idFilial;
	}

	public String getFilial() {
		return filial;
	}

	public void setFilial(String filial) {
		this.filial = filial;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
