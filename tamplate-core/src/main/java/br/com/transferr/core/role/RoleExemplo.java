package br.com.transferr.core.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.transferr.core.dao.DAOExemplo;
import br.com.transferr.core.exceptions.ValidationException;
import br.com.transferr.core.model.Exemplo;


@Service
public class RoleExemplo extends RoleSuperClass<Exemplo> {

	
	public RoleExemplo() {
		System.out.println(this.getClass().getName());
	}
	@Autowired
	private DAOExemplo daoExemplo;
	@Override
	public Exemplo insert(Exemplo entidade) throws ValidationException {
		return daoExemplo.insert(entidade);
	}

	@Override
	public void delete(long codigo) throws ValidationException {
		daoExemplo.delete(codigo);
	}

	@Override
	public Exemplo update(Exemplo entidade) throws ValidationException {
		return daoExemplo.update(entidade);
	}

	@Override
	public Exemplo find(long codigo) throws ValidationException {
		return daoExemplo.find(codigo);
	}

}
