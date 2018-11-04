package com.reservalab.filter;

import com.reservalab.model.Curso;


public class LaboratorioFilter {

private Integer id;
private Curso cursofk;
private String nome;
private String localizacao;
private String tamanho;
private String equipamentos;
private String tipo;
private String situacao;


public Integer getId() {
  return this.id;
}
public void setId(Integer id) {
  this.id = id;
}
public Curso getCursofk() {
  return this.cursofk;
}
public void setCursofk(Curso cursofk) {
  this.cursofk = cursofk;
}
public String getNome() {
  return this.nome;
}
public void setNome(String nome) {
  this.nome = nome;
}
public String getLocalizacao() {
  return this.localizacao;
}
public void setLocalizacao(String localizacao) {
  this.localizacao = localizacao;
}
public String getTamanho() {
  return this.tamanho;
}
public void setTamanho(String tamanho) {
  this.tamanho = tamanho;
}
public String getEquipamentos() {
  return this.equipamentos;
}
public void setEquipamentos(String equipamentos) {
  this.equipamentos = equipamentos;
}
public String getTipo() {
  return this.tipo;
}
public void setTipo(String tipo) {
  this.tipo = tipo;
}
public String getSituacao() {
  return this.situacao;
}
public void setSituacao(String situacao) {
  this.situacao = situacao;
}



}
