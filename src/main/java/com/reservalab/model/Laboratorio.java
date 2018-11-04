package com.reservalab.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "laboratorio")
public class Laboratorio {
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotNull(message = "Laboratório precisa ser preenchido")
	@ManyToOne
	private Curso cursofk;
	
	@NotBlank(message = "Nome precisa ser preenchido")
	private String nome;
	
	@NotBlank(message = "Localização precisa ser preenchido")
	private String localizacao;
	
	@NotBlank(message = "Tamanho precisa ser preenchido")
	private String tamanho;
	
	@NotBlank(message = "Equipamentos precisa ser preenchido")
	private String equipamentos;
	
	@NotBlank(message = "Tipo precisa ser preenchido")
	private String tipo;
	
	@NotBlank(message = "Situação precisa ser preenchido")
	private String situacao;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Laboratorio other = (Laboratorio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Curso getCursofk() {
		return cursofk;
	}
	public void setCursofk(Curso cursofk) {
		this.cursofk = cursofk;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	public String getEquipamentos() {
		return equipamentos;
	}
	public void setEquipamentos(String equipamentos) {
		this.equipamentos = equipamentos;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
}
