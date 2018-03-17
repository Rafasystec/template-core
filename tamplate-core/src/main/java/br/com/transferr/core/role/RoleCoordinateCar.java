package br.com.transferr.core.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.transferr.core.dao.CoordinateCarDAO;
import br.com.transferr.core.exceptions.ValidationException;
import br.com.transferr.core.model.CoordinateCar;


@Service
public class RoleCoordinateCar extends RoleSuperClass<CoordinateCar> {

	
	public RoleCoordinateCar() {
		System.out.println(this.getClass().getName());
	}
	@Autowired
	private CoordinateCarDAO coordinateCarDao;
	@Override
	public CoordinateCar insert(CoordinateCar entidade) throws ValidationException {
		return coordinateCarDao.insert(entidade);
	}

	@Override
	public void delete(long codigo) throws ValidationException {
		coordinateCarDao.delete(codigo);
	}

	@Override
	public CoordinateCar update(CoordinateCar entidade) throws ValidationException {
		return coordinateCarDao.update(entidade);
	}

	@Override
	public CoordinateCar find(long codigo) throws ValidationException {
		return coordinateCarDao.find(codigo);
	}
	
	public void updateCoordinateByCar(Long idCar, Double latitude, Double longitude) throws ValidationException {
		 coordinateCarDao.updateCoordinateByCar(idCar, latitude, longitude);
	}
	
	public CoordinateCar findByCarId(Long carID) {
		return coordinateCarDao.findByCarId(carID);
	}

}
