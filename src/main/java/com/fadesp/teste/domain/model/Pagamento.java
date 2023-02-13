package com.fadesp.teste.domain.model;

import java.math.BigDecimal;

import com.fadesp.teste.domain.enums.MetodoPagamentoEnum;
import com.fadesp.teste.domain.enums.StatusPagamentoEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Pagamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Favor informe o código de débito, pois é um campo obrigatório")
	private Integer codigoDebito;
	
	@Column(name="CpfCnpj")
	@NotNull(message = "Favor informe um CPF ou CNPJ, pois é um campo obrigatório")
	@Size(min = 11, max = 14, message = "Esse campo deve ter de 11 a 14 caracteres")
	private String cpfCnpj;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Favor informe o metodo de pagamento, pois é um campo obrigatório")
	private MetodoPagamentoEnum metodoPagamento;
	
	@Size(min = 16, message = "Esse campo deve ter 16 caracteres")
	private String numeroCartao;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusPagamentoEnum statusPagamento;
	
	@NotNull(message = "Favor informe o valor do pagamento, pois é um campo obrigatório")
	private BigDecimal valorPagamento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCodigoDebito() {
		return codigoDebito;
	}

	public void setCodigoDebito(Integer codigoDebito) {
		this.codigoDebito = codigoDebito;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public MetodoPagamentoEnum getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setMetodoPagamento(MetodoPagamentoEnum metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public StatusPagamentoEnum getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(StatusPagamentoEnum string) {
		this.statusPagamento = string;
	}

	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

}
