package br.com.transferr.core.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.transferr.core.metadata.CoordinatesAmplitude;
import br.com.transferr.core.metadata.CoordinatesQuadrant;
import br.com.transferr.core.model.CoordinatePassenger;
import br.com.transferr.core.responses.ResponseCarsOnline;
import br.com.transferr.core.responses.ResponsePassengersOnline;
import br.com.transferr.core.util.CoordinatesUtil;



@Repository
public class DAOCoordinatePassenger extends SuperClassDAO<CoordinatePassenger> {

	public DAOCoordinatePassenger() {
	}
	@Override
	public CoordinatePassenger find(long codigo) {
		try{
			return getManager().find(CoordinatePassenger.class, codigo);
		}catch(NoResultException e){
			return null;
		}
	}
	
	public void exempleHowToManuallyRemoveInstancesFromPersistenceContext() {
		CoordinatePassenger exemplo = find(1l);
		//You don't have to wait for the persistence context to close.
		//You can evict entity instances manually
		getManager().detach(exemplo);
		if(getManager().contains(exemplo)){
			//It will never enter here because the entity was removed
		}
		//Any changes has no effect
	}
	
	public  List<ResponsePassengersOnline> getPassengersOnline(CoordinatesQuadrant coordinates){
		StringBuilder query = new StringBuilder();
		StringBuilder where = new StringBuilder();
		query.append("SELECT                         ").append("\n");
		query.append("    co.id as id,              ").append("\n");
	    query.append("    co.latitude  AS latitude,  ").append("\n");
	    query.append("    co.longitude AS longitude  ").append("\n");
	    query.append("  FROM coordinate_pass co      ").append("\n");
	    where.append("  WHERE                         ").append("\n");
	    
	    CoordinatesAmplitude amplitude 	= CoordinatesUtil.defineCoordinates(coordinates);
	    where.append("  (co.longitude BETWEEN ").append(amplitude.getMinLongitude()).append(" AND ").append(amplitude.getMaxLongitude()).append(") ").append(" AND ")
		 .append(" (co.latitude  BETWEEN ").append(amplitude.getMinLatitude()) .append(" AND ").append(amplitude.getMaxLatitude()).append(") ");
	    query.append(where.toString());	
	   
	    Query qry = getManager().createNativeQuery(query.toString(), ResponsePassengersOnline.NAME);
		
		try{
			return qry.getResultList();
		}catch(NoResultException e){
			return null;
		}
}

}
