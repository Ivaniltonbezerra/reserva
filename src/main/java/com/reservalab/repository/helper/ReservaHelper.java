package com.reservalab.repository.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.reservalab.filter.ReservaFilter;
import com.reservalab.model.Reserva;



public interface ReservaHelper {

	public Page<Reserva> filtrar(ReservaFilter reservaFilter, Pageable pageable);

}
