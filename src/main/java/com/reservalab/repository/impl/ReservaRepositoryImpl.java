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

import com.reservalab.repository.helper.ReservaHelper;
import com.reservalab.filter.ReservaFilter;
import com.reservalab.model.Reserva;



public class ReservaRepositoryImpl implements ReservaHelper {

	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public Page<Reserva> filtrar(ReservaFilter  reservaFilter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Reserva.class);

		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;

		criteria.setFirstResult(primeiroRegistro);
		criteria.setMaxResults(totalRegistrosPorPagina);

		addFilter(reservaFilter, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(reservaFilter));
	}

	private Long total(ReservaFilter reservaFilter) {

		Criteria criteria = manager.unwrap(Session.class).createCriteria(Reserva.class);
		addFilter(reservaFilter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void addFilter(ReservaFilter reservaFilter, Criteria criteria) {
		if (reservaFilter != null) {
			
                      if (reservaFilter.getId() != null) {
				criteria.add(Restrictions.eq("id", reservaFilter.getId()));
			}

            if (reservaFilter.getLaboratoriofk() != null) {
				criteria.add(Restrictions.eq("laboratoriofk", reservaFilter.getLaboratoriofk()));
			}

            if (reservaFilter.getUsuariofk() != null) {
				criteria.add(Restrictions.eq("usuariofk", reservaFilter.getUsuariofk()));
			}

           if (!StringUtils.isEmpty(reservaFilter.getTurno())) {
				criteria.add(Restrictions.ilike("turno", reservaFilter.getTurno(), MatchMode.ANYWHERE));
			}
			

           if (!StringUtils.isEmpty(reservaFilter.getObs())) {
				criteria.add(Restrictions.ilike("obs", reservaFilter.getObs(), MatchMode.ANYWHERE));
			}
			

           if (!StringUtils.isEmpty(reservaFilter.getSituacao())) {
				criteria.add(Restrictions.ilike("situacao", reservaFilter.getSituacao(), MatchMode.ANYWHERE));
			}
			

            if (reservaFilter.getDataReserva() != null) {
				criteria.add(Restrictions.eq("dataReserva", reservaFilter.getDataReserva()));
			}



		}
	}

}
