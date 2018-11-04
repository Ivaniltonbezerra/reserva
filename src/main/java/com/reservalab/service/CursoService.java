package com.reservalab.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.reservalab.model.Curso;
import com.reservalab.repository.CursoRepository;
import com.reservalab.filter.CursoFilter;

@Service
public class CursoService {

	private String errorDelete = "this record is related to other tables.";

    @Autowired
	private CursoRepository cursoRepository;

	@Transactional
	public void save(Curso curso) {
		cursoRepository.save(curso);
	}
	
	public Page<Curso> filter(CursoFilter cursoFilter, Pageable pageable) {
		return cursoRepository.filtrar(cursoFilter, pageable);
	}

	@Transactional
	public void delete(Curso curso) {
		try {
			cursoRepository.delete(curso);
		} catch (Exception e) {
			if (e instanceof org.hibernate.exception.ConstraintViolationException
					|| e instanceof DataIntegrityViolationException) {
				throw new IllegalArgumentException(errorDelete);
			}
			throw e;
		}

	}

}
