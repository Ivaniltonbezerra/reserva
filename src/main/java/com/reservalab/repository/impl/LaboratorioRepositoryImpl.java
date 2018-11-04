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

import com.reservalab.repository.helper.LaboratorioHelper;
import com.reservalab.filter.LaboratorioFilter;
import com.reservalab.model.Laboratorio;



public class LaboratorioRepositoryImpl implements LaboratorioHelper {

	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public Page<Laboratorio> filtrar(LaboratorioFilter  laboratorioFilter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Laboratorio.class);

		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;

		criteria.setFirstResult(primeiroRegistro);
		criteria.setMaxResults(totalRegistrosPorPagina);

		addFilter(laboratorioFilter, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(laboratorioFilter));
	}

	private Long total(LaboratorioFilter laboratorioFilter) {

		Criteria criteria = manager.unwrap(Session.class).createCriteria(Laboratorio.class);
		addFilter(laboratorioFilter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void addFilter(LaboratorioFilter laboratorioFilter, Criteria criteria) {
		if (laboratorioFilter != null) {
			
                      if (laboratorioFilter.getId() != null) {
				criteria.add(Restrictions.eq("id", laboratorioFilter.getId()));
			}

            if (laboratorioFilter.getCursofk() != null) {
				criteria.add(Restrictions.eq("cursofk", laboratorioFilter.getCursofk()));
			}

           if (!StringUtils.isEmpty(laboratorioFilter.getNome())) {
				criteria.add(Restrictions.ilike("nome", laboratorioFilter.getNome(), MatchMode.ANYWHERE));
			}
			

           if (!StringUtils.isEmpty(laboratorioFilter.getLocalizacao())) {
				criteria.add(Restrictions.ilike("localizacao", laboratorioFilter.getLocalizacao(), MatchMode.ANYWHERE));
			}
			

           if (!StringUtils.isEmpty(laboratorioFilter.getTamanho())) {
				criteria.add(Restrictions.ilike("tamanho", laboratorioFilter.getTamanho(), MatchMode.ANYWHERE));
			}
			

           if (!StringUtils.isEmpty(laboratorioFilter.getEquipamentos())) {
				criteria.add(Restrictions.ilike("equipamentos", laboratorioFilter.getEquipamentos(), MatchMode.ANYWHERE));
			}
			

           if (!StringUtils.isEmpty(laboratorioFilter.getTipo())) {
				criteria.add(Restrictions.ilike("tipo", laboratorioFilter.getTipo(), MatchMode.ANYWHERE));
			}
			

           if (!StringUtils.isEmpty(laboratorioFilter.getSituacao())) {
				criteria.add(Restrictions.ilike("situacao", laboratorioFilter.getSituacao(), MatchMode.ANYWHERE));
			}
			



		}
	}

}
