package com.fadesp.teste.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.fadesp.teste.domain.model.Pagamento;
import com.fadesp.teste.repository.PagamentoRepository;
import jakarta.transaction.Transactional;

@Service
public class PagamentoService {

	final PagamentoRepository pagamentoRepository;
	
	public PagamentoService(PagamentoRepository pagamentoRepository) {
		this.pagamentoRepository = pagamentoRepository;
	}
	
	@Transactional
	public Pagamento salvar(Pagamento pagamento) {
		return pagamentoRepository.save(pagamento);
	}

	public List<Pagamento> listarTodos() {
		return pagamentoRepository.findAll();
	}

	public Optional<Pagamento> listarPorId(long id) {
		return pagamentoRepository.findById(id);
	}

	@Transactional
	public void excluir(Pagamento pagamento) {
		pagamentoRepository.delete(pagamento);
	}
	
	public List<Pagamento> findBycodigoDebito(Integer codigoDebito) {
	 return pagamentoRepository.findBycodigoDebito(codigoDebito);
	
	}

	public List<Pagamento> findBycpfCnpj(String cpfCnpj) {
		return pagamentoRepository.findBycpfCnpj(cpfCnpj);
	}
}
