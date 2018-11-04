package com.reservalab.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "aluno")
public class Usuario {

	@Id
	@GeneratedValue
	private Integer id;
	
	@NotNull(message = "Login precisa ser preenchido")
	@ManyToOne
	private Curso cursofk;
	
	@NotBlank(message = "Login precisa ser preenchido")
	private String login;
	
	@NotBlank(message = "Senha precisa ser preenchido")
	private String senha;
	
	@NotBlank(message = "Email precisa ser preenchido")
	private String email;
	
	@NotBlank(message = "CPF precisa ser preenchido")
	private String cpf;
	
	@NotBlank(message = "Perfil precisa ser preenchido")
	private String perfil;
	
	@NotBlank(message = "Celular precisa ser preenchido")
	private String celular;
	
	@NotBlank(message = "Nome precisa ser preenchido")
	private String nome;

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
		Usuario other = (Usuario) obj;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
