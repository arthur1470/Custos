package br.com.custos.dao;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@SessionScoped
public abstract class DAO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public <T> void guardar(T entity) {
		manager.merge(entity);
	}

	public <T> T carregar(Class<T> clazz, Object key) {
		return manager.find(clazz, key);
	}

	public <T> void remover(T entity) {
		manager.remove(manager.contains(entity)? entity : manager.merge(entity));	
	}

	public <T> void alterar(T entity) {
		manager.merge(entity);
	}

	protected Query criarQuery(String query) {
		return manager.createQuery(query);
	}
}
