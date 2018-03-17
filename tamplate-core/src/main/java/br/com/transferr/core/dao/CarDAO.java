package br.com.transferr.core.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.transferr.core.metadata.CoordinatesAmplitude;
import br.com.transferr.core.metadata.CoordinatesQuadrant;
import br.com.transferr.core.model.Car;
import br.com.transferr.core.responses.ResponseCarsOnline;
import br.com.transferr.core.util.CoordinatesUtil;

@Repository
public class CarDAO extends SuperClassDAO<Car> {

	public CarDAO() {

	}

	@Override
	public Car find(long codigo) {
		return manager.find(Car.class, codigo);
	}


	public Car getCarByDriver(String idDriver) {
		TypedQuery<Car> qry = getManager().createNamedQuery(Car.FIND_BY_DRIVER, Car.class)
				.setParameter("idDriver", idDriver);

		Car car = null;
		try{
			car = qry.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
		return car;
	}
	
	
	public  List<ResponseCarsOnline> getCarsOnline(CoordinatesQuadrant coordinates){
		StringBuilder query = new StringBuilder();
		StringBuilder where = new StringBuilder();
		query.append("SELECT                         ").append("\n");
		query.append("    car.id as id,              ").append("\n");
	    query.append("    ' ' as photo,              ").append("\n");
	    query.append("    car.model as model,        ").append("\n");
	    query.append("    car.car_identity as placa, ").append("\n");
	    query.append("    car.color as cor ,         ").append("\n");
	    query.append("    driver.name as name_driver,").append("\n");
	    query.append("    co.latitude  AS latitude,  ").append("\n");
	    query.append("    co.longitude AS longitude  ").append("\n");
	    query.append("  FROM car                     ").append("\n");
	    query.append("    INNER JOIN driver ON       ").append("\n");
	    query.append("      car.id_driver = driver.id").append("\n");
	    query.append("    INNER JOIN coordinate_car co ON  ").append("\n");
	    query.append("      co.id_car = car.id             ").append("\n");
	    where.append("  WHERE                              ").append("\n");
	    where.append("     car.STATUS <> 2                 ").append("\n");
	    CoordinatesAmplitude amplitude 	= CoordinatesUtil.defineCoordinates(coordinates);
	    where.append(" AND (co.longitude BETWEEN ").append(amplitude.getMinLongitude()).append(" AND ").append(amplitude.getMaxLongitude()).append(") ").append(" AND ")
		 .append(" (co.latitude  BETWEEN ").append(amplitude.getMinLatitude()) .append(" AND ").append(amplitude.getMaxLatitude()).append(") ");
	    query.append(where.toString());	
	   
	    Query qry = getManager().createNativeQuery(query.toString(), ResponseCarsOnline.NAME);
		
		try{
			return qry.getResultList();
		}catch(NoResultException e){
			return null;
		}
	}
	
	public Car getCarByUser(Long idUser){
		String jpql = "FROM Car WHERE driver.user.id = :idUser";
		TypedQuery<Car> qry = getManager().createQuery(jpql, Car.class)
				.setParameter("idUser", idUser);
		try{
			return qry.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	
	
	

}