package com.fadesp.teste.domain.enums;

public enum MetodoPagamentoEnum {
	 	BOLETO(0),
	    PIX(1),
	    CARTAO_CREDITO(2),
	    CARTAO_DEBITO(3);

	private Integer valor;

	private MetodoPagamentoEnum(Integer valor) {
		this.valor = valor;
	}

	public Integer getValor() { 
		return valor; 
		}
}
