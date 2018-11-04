package com.reservalab.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reservalab.model.Reserva;
import com.reservalab.repository.helper.ReservaHelper;



@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer>,ReservaHelper {

	

}

