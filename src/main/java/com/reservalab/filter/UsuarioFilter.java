package com.reservalab.filter;

import com.reservalab.model.Curso;


public class UsuarioFilter {

private Integer id;
private Curso cursofk;
private String login;
private String senha;
private String email;
private String cpf;
private String perfil;
private String celular;
private String nome;


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
public String getLogin() {
  return this.login;
}
public void setLogin(String login) {
  this.login = login;
}
public String getSenha() {
  return this.senha;
}
public void setSenha(String senha) {
  this.senha = senha;
}
public String getEmail() {
  return this.email;
}
public void setEmail(String email) {
  this.email = email;
}
public String getCpf() {
  return this.cpf;
}
public void setCpf(String cpf) {
  this.cpf = cpf;
}
public String getPerfil() {
  return this.perfil;
}
public void setPerfil(String perfil) {
  this.perfil = perfil;
}
public String getCelular() {
  return this.celular;
}
public void setCelular(String celular) {
  this.celular = celular;
}
public String getNome() {
  return this.nome;
}
public void setNome(String nome) {
  this.nome = nome;
}



}
