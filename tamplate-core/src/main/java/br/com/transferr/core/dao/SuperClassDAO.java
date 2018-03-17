package br.com.transferr.core.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.transferr.core.model.Entidade;

public  abstract class SuperClassDAO<T extends Entidade>  {
	public abstract T find(long codigo) ;
	@PersistenceContext
	protected EntityManager manager;
	public SuperClassDAO() {
	}
	
	public T insert(T entidade){
		manager.persist(entidade);
		return entidade;
	}

	public void delete(long codigo){
		T entidade = this.find(codigo);
		if(entidade != null){
			manager.merge(entidade);
			manager.remove(entidade);
		}else {
			//throw new NotFoundRecordException();
		}
	}

	public void delete(T entidade) {
		manager.remove(entidade);
	}

	public T update(T entidade){		
		manager.merge(entidade);
		return entidade;
	}

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public T find(Class<T> type, long id){
		return this.manager.find(type, id);
	}

}
