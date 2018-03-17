package br.com.transferr.core.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="EXEMPLO")
public class Exemplo extends Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6630791642533171490L;
	@Column(name="VALOR",scale=2,precision=10)
	private BigDecimal valor;
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
}
