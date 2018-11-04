package com.reservalab.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "reserva")
public class Reserva {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotNull(message = "Laboratório precisa ser selecionado")
	@ManyToOne
	private Laboratorio laboratoriofk;
	
	@NotNull(message = "Usuário precisa ser selecionado")
	@ManyToOne
	private Usuario usuariofk;
	
	@NotBlank(message = "Turno precisa ser preenchido")
	private String turno;
	
	@NotBlank(message = "Observação precisa ser preenchida")
	private String obs;
	
	@NotBlank(message = "Situação precisa ser preenchida")
	private String situacao;
	
	@NotNull(message = "Data de reserva precisa ser preenchida")
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	private Date dataReserva;

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
		Reserva other = (Reserva) obj;
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

	public Laboratorio getLaboratoriofk() {
		return laboratoriofk;
	}

	public void setLaboratoriofk(Laboratorio laboratoriofk) {
		this.laboratoriofk = laboratoriofk;
	}

	public Usuario getUsuariofk() {
		return usuariofk;
	}

	public void setUsuariofk(Usuario usuariofk) {
		this.usuariofk = usuariofk;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Date getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(Date dataReserva) {
		this.dataReserva = dataReserva;
	}

		
}
