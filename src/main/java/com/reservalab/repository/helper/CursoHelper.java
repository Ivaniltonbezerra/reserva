package com.reservalab.repository.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.reservalab.filter.CursoFilter;
import com.reservalab.model.Curso;



public interface CursoHelper {

	public Page<Curso> filtrar(CursoFilter cursoFilter, Pageable pageable);

}
