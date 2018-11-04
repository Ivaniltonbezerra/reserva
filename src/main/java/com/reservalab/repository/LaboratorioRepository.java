package com.reservalab.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reservalab.model.Laboratorio;
import com.reservalab.repository.helper.LaboratorioHelper;



@Repository
public interface LaboratorioRepository extends JpaRepository<Laboratorio, Integer>,LaboratorioHelper {

	

}

