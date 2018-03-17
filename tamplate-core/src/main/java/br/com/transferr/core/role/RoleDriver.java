package br.com.transferr.core.role;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.transferr.core.dao.DriverDAO;
import br.com.transferr.core.exceptions.ValidationException;
import br.com.transferr.core.model.Driver;
import br.com.transferr.core.util.HelperBase64;
import br.com.transferr.core.util.HelperVariables;

@Service
public class RoleDriver  extends RoleSuperClass<Driver> {

	
	public RoleDriver() {
		System.out.println(this.getClass().getName());
	}
	@Autowired
	private DriverDAO driverDAO;
	@Override
	public Driver insert(Driver entidade) throws ValidationException {
		return driverDAO.insert(entidade);
	}

	@Override
	public void delete(long codigo) throws ValidationException {
		driverDAO.delete(codigo);
	}

	@Override
	public Driver update(Driver entidade) throws ValidationException {
		return driverDAO.update(entidade);
	}

	@Override
	public Driver find(long codigo) throws ValidationException {
		return driverDAO.find(codigo);
	}
	
	

	
	public void saveProfilePhotosOnFileSystem(String idCar , String base64Photo) {
		
			
			if(base64Photo != null && base64Photo.trim().isEmpty() == false){
				try {
					File file = checkAndCreateTheImagesServerRepository(idCar);
					File fileImage = new File(file.getAbsolutePath()+File.separator+HelperVariables.DEFAULT_NAME_FOR_PHOTO);
					System.out.println("Directory image file:"+fileImage.getAbsolutePath());
					HelperBase64.toFile(base64Photo, fileImage);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		
	}
	
	private File checkAndCreateTheImagesServerRepository(String idCar) {
		File file = new File(RoleParametros.paramPathRepoImagens+File.separator+"Car"+File.separator+idCar);
		if(!file.exists()){
			file.mkdirs();
		}
		return file;
	}
	
	public Driver getDriverByCar(Long idCar) throws ValidationException {
		Driver driver = driverDAO.getDriverByCar(idCar);
		if(driver != null){
			return driver;
		}
		throw new ValidationException("Nenum motorista encontrado para este Veículo.");
	}
	
}
