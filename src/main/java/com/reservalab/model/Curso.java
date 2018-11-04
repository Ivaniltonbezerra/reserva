package com.reservalab.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "curso")
public class Curso {

	@Id
	@GeneratedValue
	private Integer id;
	
	@NotBlank(message = "Descrição precisa ser preenchido")
	@Column(name = "descricao")
	private String rf01;

	@Transient
	private String privateKey;
	
	@Transient
	private String hashDescricao;
	
	
	
	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getRf01() {
		return rf01;
	}

	public void setRf01(String rf01) {
		this.rf01 = rf01;
	}

	public String getHashDescricao() {
		return hashDescricao;
	}

	public void setHashDescricao(String hashDescricao) {
		this.hashDescricao = hashDescricao;
	}

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
		Curso other = (Curso) obj;
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

}
