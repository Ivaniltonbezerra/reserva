package com.reservalab.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.reservalab.model.Laboratorio;
import com.reservalab.repository.LaboratorioRepository;
import com.reservalab.filter.LaboratorioFilter;

@Service
public class LaboratorioService {

	private String errorDelete = "this record is related to other tables.";

    @Autowired
	private LaboratorioRepository laboratorioRepository;

	@Transactional
	public void save(Laboratorio laboratorio) {
		laboratorioRepository.save(laboratorio);
	}
	
	public Page<Laboratorio> filter(LaboratorioFilter laboratorioFilter, Pageable pageable) {
		return laboratorioRepository.filtrar(laboratorioFilter, pageable);
	}

	@Transactional
	public void delete(Laboratorio laboratorio) {
		try {
			laboratorioRepository.delete(laboratorio);
		} catch (Exception e) {
			if (e instanceof org.hibernate.exception.ConstraintViolationException
					|| e instanceof DataIntegrityViolationException) {
				throw new IllegalArgumentException(errorDelete);
			}
			throw e;
		}

	}

}
