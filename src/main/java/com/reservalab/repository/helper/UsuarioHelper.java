package com.reservalab.repository.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.reservalab.filter.UsuarioFilter;
import com.reservalab.model.Usuario;



public interface UsuarioHelper {

	public Page<Usuario> filtrar(UsuarioFilter usuarioFilter, Pageable pageable);

}
