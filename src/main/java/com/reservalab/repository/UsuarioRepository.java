package com.reservalab.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reservalab.model.Usuario;
import com.reservalab.repository.helper.UsuarioHelper;



@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>,UsuarioHelper {

	

}

