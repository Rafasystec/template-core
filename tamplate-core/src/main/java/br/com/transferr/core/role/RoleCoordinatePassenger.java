package br.com.transferr.core.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.transferr.core.dao.DAOCoordinatePassenger;
import br.com.transferr.core.exceptions.ValidationException;
import br.com.transferr.core.metadata.CoordinatesQuadrant;
import br.com.transferr.core.model.CoordinatePassenger;
import br.com.transferr.core.responses.ResponsePassengersOnline;


@Service
public class RoleCoordinatePassenger extends RoleSuperClass<CoordinatePassenger> {

	
	public RoleCoordinatePassenger() {
		System.out.println(this.getClass().getName());
	}
	@Autowired
	private DAOCoordinatePassenger daoCoordinatePassenger;
	@Override
	public CoordinatePassenger insert(CoordinatePassenger entidade) throws ValidationException {
		return daoCoordinatePassenger.insert(entidade);
	}

	@Override
	public void delete(long codigo) throws ValidationException {
		daoCoordinatePassenger.delete(codigo);
	}

	@Override
	public CoordinatePassenger update(CoordinatePassenger entidade) throws ValidationException {
		return daoCoordinatePassenger.update(entidade);
	}

	@Override
	public CoordinatePassenger find(long codigo) throws ValidationException {
		return daoCoordinatePassenger.find(codigo);
	}
	
	public  List<ResponsePassengersOnline> getPassengersOnline(CoordinatesQuadrant coordinates){
		return daoCoordinatePassenger.getPassengersOnline(coordinates);
	}

}
