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

import com.reservalab.repository.helper.UsuarioHelper;
import com.reservalab.filter.UsuarioFilter;
import com.reservalab.model.Usuario;



public class UsuarioRepositoryImpl implements UsuarioHelper {

	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public Page<Usuario> filtrar(UsuarioFilter  usuarioFilter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Usuario.class);

		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;

		criteria.setFirstResult(primeiroRegistro);
		criteria.setMaxResults(totalRegistrosPorPagina);

		addFilter(usuarioFilter, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(usuarioFilter));
	}

	private Long total(UsuarioFilter usuarioFilter) {

		Criteria criteria = manager.unwrap(Session.class).createCriteria(Usuario.class);
		addFilter(usuarioFilter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void addFilter(UsuarioFilter usuarioFilter, Criteria criteria) {
		if (usuarioFilter != null) {
			
                      if (usuarioFilter.getId() != null) {
				criteria.add(Restrictions.eq("id", usuarioFilter.getId()));
			}

            if (usuarioFilter.getCursofk() != null) {
				criteria.add(Restrictions.eq("cursofk", usuarioFilter.getCursofk()));
			}

           if (!StringUtils.isEmpty(usuarioFilter.getLogin())) {
				criteria.add(Restrictions.ilike("login", usuarioFilter.getLogin(), MatchMode.ANYWHERE));
			}
			

           if (!StringUtils.isEmpty(usuarioFilter.getSenha())) {
				criteria.add(Restrictions.ilike("senha", usuarioFilter.getSenha(), MatchMode.ANYWHERE));
			}
			

           if (!StringUtils.isEmpty(usuarioFilter.getEmail())) {
				criteria.add(Restrictions.ilike("email", usuarioFilter.getEmail(), MatchMode.ANYWHERE));
			}
			

           if (!StringUtils.isEmpty(usuarioFilter.getCpf())) {
				criteria.add(Restrictions.ilike("cpf", usuarioFilter.getCpf(), MatchMode.ANYWHERE));
			}
			

           if (!StringUtils.isEmpty(usuarioFilter.getPerfil())) {
				criteria.add(Restrictions.ilike("perfil", usuarioFilter.getPerfil(), MatchMode.ANYWHERE));
			}
			

           if (!StringUtils.isEmpty(usuarioFilter.getCelular())) {
				criteria.add(Restrictions.ilike("celular", usuarioFilter.getCelular(), MatchMode.ANYWHERE));
			}
			

           if (!StringUtils.isEmpty(usuarioFilter.getNome())) {
				criteria.add(Restrictions.ilike("nome", usuarioFilter.getNome(), MatchMode.ANYWHERE));
			}
			



		}
	}

}
