package br.com.transferr.core.exceptions;

public class ValidationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6524741300778460580L;
	
	private String campoErro="";// preencher sempre em camelCase exatamente igual o da entidade
	public ValidationException(String message) {
		super(message);
	}
	public ValidationException(String message,String campoErro) {
		super(message);
		this.campoErro=campoErro;
	}
	public String getCampoErro() {
		return campoErro;
	}
	
}
 