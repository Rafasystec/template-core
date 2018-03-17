package br.com.transferr.core.dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.transferr.core.model.User;




@Repository
public class UserDAO extends SuperClassDAO<User> {

	public UserDAO() {
		 
	}
	
		@Override
	public User find(long codigo) {
		return manager.find(User.class, codigo);
	}
		
		
	public User getUsuarioLogin(String email, String password) {
			TypedQuery<User> qry = getManager().createNamedQuery(User.FIND_BY_LOGIN, User.class)
					.setParameter("email", email)
					.setParameter("password", password);
			User user = null;
			try{
				user = qry.getSingleResult();
			}catch(NoResultException e){
				return null;
			}
			return user;
	}
	
	public User getUsuarioEmail(String email) {
		TypedQuery<User> qry = getManager().createNamedQuery(User.FIND_BY_EMAIL, User.class)
				.setParameter("email", email);
				
		User user = null;
		try{
			user = qry.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
		return user;
}

}