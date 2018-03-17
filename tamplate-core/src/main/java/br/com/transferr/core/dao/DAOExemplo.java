package br.com.transferr.core.dao;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import br.com.transferr.core.model.Exemplo;



@Repository
public class DAOExemplo extends SuperClassDAO<Exemplo> {

	public DAOExemplo() {
	}
	@Override
	public Exemplo find(long codigo) {
		try{
			return getManager().find(Exemplo.class, codigo);
		}catch(NoResultException e){
			return null;
		}
	}
	
	public void exempleHowToManuallyRemoveInstancesFromPersistenceContext() {
		Exemplo exemplo = find(1l);
		//You don't have to wait for the persistence context to close.
		//You can evict entity instances manually
		getManager().detach(exemplo);
		if(getManager().contains(exemplo)){
			//It will never enter here because the entity was removed
		}
		//Any changes has no effect
	}

}
