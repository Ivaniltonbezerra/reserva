package com.reservalab.filter;

import com.reservalab.model.Laboratorio;
import com.reservalab.model.Usuario;
import java.util.Date;


public class ReservaFilter {

private Integer id;
private Laboratorio laboratoriofk;
private Usuario usuariofk;
private String turno;
private String obs;
private String situacao;
private Date dataReserva;


public Integer getId() {
  return this.id;
}
public void setId(Integer id) {
  this.id = id;
}
public Laboratorio getLaboratoriofk() {
  return this.laboratoriofk;
}
public void setLaboratoriofk(Laboratorio laboratoriofk) {
  this.laboratoriofk = laboratoriofk;
}
public Usuario getUsuariofk() {
  return this.usuariofk;
}
public void setUsuariofk(Usuario usuariofk) {
  this.usuariofk = usuariofk;
}
public String getTurno() {
  return this.turno;
}
public void setTurno(String turno) {
  this.turno = turno;
}
public String getObs() {
  return this.obs;
}
public void setObs(String obs) {
  this.obs = obs;
}
public String getSituacao() {
  return this.situacao;
}
public void setSituacao(String situacao) {
  this.situacao = situacao;
}
public Date getDataReserva() {
  return this.dataReserva;
}
public void setDataReserva(Date dataReserva) {
  this.dataReserva = dataReserva;
}



}
