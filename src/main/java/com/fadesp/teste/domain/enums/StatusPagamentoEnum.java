package com.fadesp.teste.domain.enums;

public enum StatusPagamentoEnum {
	PENDENTE_PROCESSAMENTO(0),
    PROCESSADO_SUCESSO(1),
    PROCESSADO_FALHA(2);

private Integer valor;

	private StatusPagamentoEnum(Integer valor) {
		this.valor = valor;
	}

	public Integer getValor() { 
		return valor; 
		}
	
}
