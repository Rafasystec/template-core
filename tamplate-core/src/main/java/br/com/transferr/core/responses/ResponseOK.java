package br.com.transferr.core.responses;

public class ResponseOK {

	private String descricao = "OK";

	public ResponseOK() {
	}
	public ResponseOK(String descricao) {
		super();
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
