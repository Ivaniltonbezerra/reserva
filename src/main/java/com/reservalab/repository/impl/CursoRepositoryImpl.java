package com.reservalab.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.reservalab.repository.helper.CursoHelper;
import com.reservalab.filter.CursoFilter;
import com.reservalab.model.Curso;



public class CursoRepositoryImpl implements CursoHelper {

	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public Page<Curso> filtrar(CursoFilter  cursoFilter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Curso.class);

		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;

		criteria.setFirstResult(primeiroRegistro);
		criteria.setMaxResults(totalRegistrosPorPagina);

		addFilter(cursoFilter, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(cursoFilter));
	}

	private Long total(CursoFilter cursoFilter) {

		Criteria criteria = manager.unwrap(Session.class).createCriteria(Curso.class);
		addFilter(cursoFilter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void addFilter(CursoFilter cursoFilter, Criteria criteria) {
		if (cursoFilter != null) {
			
                      if (cursoFilter.getId() != null) {
				criteria.add(Restrictions.eq("id", cursoFilter.getId()));
			}

           if (!StringUtils.isEmpty(cursoFilter.getDescricao())) {
				criteria.add(Restrictions.ilike("descricao", cursoFilter.getDescricao(), MatchMode.ANYWHERE));
			}
			



		}
	}

}
