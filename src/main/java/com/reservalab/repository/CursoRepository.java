package com.reservalab.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reservalab.model.Curso;
import com.reservalab.repository.helper.CursoHelper;



@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer>,CursoHelper {

	

}

