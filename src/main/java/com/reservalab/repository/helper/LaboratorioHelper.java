package com.reservalab.repository.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.reservalab.filter.LaboratorioFilter;
import com.reservalab.model.Laboratorio;



public interface LaboratorioHelper {

	public Page<Laboratorio> filtrar(LaboratorioFilter laboratorioFilter, Pageable pageable);

}
