package br.com.transferr.core.role;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.transferr.core.dao.CarDAO;
import br.com.transferr.core.dao.CoordinateCarDAO;
import br.com.transferr.core.enums.EnumStatus;
import br.com.transferr.core.exceptions.ValidationException;
import br.com.transferr.core.metadata.CoordinatesQuadrant;
import br.com.transferr.core.model.Car;
import br.com.transferr.core.model.CoordinateCar;
import br.com.transferr.core.model.User;
import br.com.transferr.core.responses.*;


@Service
public class RoleCar extends RoleSuperClass<Car> {

	
	public RoleCar() {
		System.out.println(this.getClass().getName());
	}
	@Autowired
	private CarDAO carDAO;
	@Autowired
	private RoleCoordinateCar roleCoordinateCar;
	@Override
	public Car insert(Car entidade) throws ValidationException {
		return carDAO.insert(entidade);
	}

	@Override
	public void delete(long codigo) throws ValidationException {
		carDAO.delete(codigo);
	}

	@Override
	public Car update(Car entidade) throws ValidationException {
		return carDAO.update(entidade);
	}

	@Override
	public Car find(long codigo) throws ValidationException {
		return carDAO.find(codigo);
	}

	public Car findCarByDriver(String idDriver) {
		return carDAO.getCarByDriver(idDriver);
	}
	
	public Car getCarByUser(Long idUser) throws ValidationException{
		Car carByUser = carDAO.getCarByUser(idUser);
		if(carByUser == null){
			throw new ValidationException("Usuário não está associado a nenhum carro.");
		}
		return carByUser;
	}
	

	public List<ResponseCarsOnline> getAvailableCars(CoordinatesQuadrant coordinates) {

		return this.carDAO.getCarsOnline(coordinates);
	}
	
	public void putCarOnlineOrOffline(RequestCoordinatesUpdate carUpdate,boolean isOnline) throws ValidationException {
		Car car = carDAO.find(carUpdate.getIdCar());
		if(car == null) {
			throw new ValidationException("Carro não cadastrado!");
		}
		if(isOnline) {
			car.setStatus(EnumStatus.WAITING_CLIENT_TOUR);
			
		}else {
			car.setStatus(EnumStatus.OFFLINE);
		}
		car = update(car);
		if(isOnline) {
			CoordinateCar coordinateCar = roleCoordinateCar.findByCarId(carUpdate.getIdCar().longValue());
			if(coordinateCar == null) {
				coordinateCar = new CoordinateCar();
				coordinateCar.setCar(car);		
				coordinateCar.setDtLastUpdate(new Date());
				coordinateCar.setLatitude(carUpdate.getLatitude());
				coordinateCar.setLongitude(carUpdate.getLongitude());
				roleCoordinateCar.insert(coordinateCar);
			}
		}
	}
	
	
}