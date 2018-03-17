package br.com.transferr.core.dao;

import java.util.Date;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import br.com.transferr.core.model.CoordinateCar;



@Repository
public class CoordinateCarDAO extends SuperClassDAO<CoordinateCar> {

	public CoordinateCarDAO() {
		 
	}
	
	@Override
	public CoordinateCar find(long codigo) {
		return manager.find(CoordinateCar.class, codigo);
	}
	
	
    public void updateCoordinateByCar(Long idCar, Double latitude, Double longitude){
    	//Session session = sessionFactory.openSession();
    	//Transaction tx = session.beginTransaction();

    	String hqlUpdate = "update CoordinateCar c set c.latitude = :latitude , c.longitude = :longitude,  c.dtLastUpdate = :dtLastUpdate where c.car.id = :idCar";
    	// or String hqlUpdate = "update Customer set name = :newName where name = :oldName";
    	manager.createQuery( hqlUpdate )
    	        .setParameter( "latitude", latitude )
    	        .setParameter( "longitude", longitude )
    	        .setParameter( "idCar", idCar )
    	        .setParameter( "dtLastUpdate", new Date())
    	        .executeUpdate();
    	//tx.commit();
    	//session.close();
    }
    
    public CoordinateCar findByCarId(Long carID) {
		try {
			return getManager().createNamedQuery(CoordinateCar.FIND_BY_CAR, CoordinateCar.class)
					.setParameter("idCar", carID)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}