package com.fadesp.teste.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fadesp.teste.domain.enums.StatusPagamentoEnum;
import com.fadesp.teste.domain.model.Pagamento;
import com.fadesp.teste.service.PagamentoService;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

	final PagamentoService pagamentoService;
	
	public PagamentoController(PagamentoService pagamentoService) {
		this.pagamentoService = pagamentoService;
	}
	
	@GetMapping
	public ResponseEntity <List<Pagamento>>listar() {
		return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.listarTodos());
	}
	
	@PostMapping 
	public ResponseEntity<Object> adicionar(@RequestBody Pagamento pagamento) {
		if(pagamento.getMetodoPagamento().getValor().equals(0)|| pagamento.getMetodoPagamento().getValor().equals(1)){
			pagamento.setNumeroCartao(null); 
		}else if (pagamento.getNumeroCartao().equals("")) {
			return	ResponseEntity.status(HttpStatus.CONFLICT).body("Favor informe o número do cartão para pagamento.");
		}
		pagamento.setStatusPagamento(StatusPagamentoEnum.PENDENTE_PROCESSAMENTO); 
	 	return	ResponseEntity.status(HttpStatus.CREATED).body(pagamentoService.salvar(pagamento));
	}
	
	@GetMapping("/{id}")
	 public ResponseEntity<Object> buscarPagamento(@PathVariable long id) {
		Optional<Pagamento> pagamentoOptional = pagamentoService.listarPorId(id);
	     if(!pagamentoOptional.isPresent()){
		      	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pagamento não encontrado através do ID: " + id);
		}
	    return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.listarPorId(id));
	 }

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletarPagamento(@PathVariable long id){
		Optional<Pagamento> pagamentoOptional = pagamentoService.listarPorId(id);
	     if(!pagamentoOptional.isPresent()){
		      	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pagamento não encontrado através do ID: " + id);
		}
	     if(!pagamentoOptional.get().getStatusPagamento().equals(StatusPagamentoEnum.PENDENTE_PROCESSAMENTO)){
	    	 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pagamento já foi processado e não pode mais ser excluído");
			}
	     pagamentoService.excluir(pagamentoOptional.get());
	     return ResponseEntity.status(HttpStatus.OK).body(pagamentoOptional.get());
	}
	
	@GetMapping("/filtrar/codigo_debito/{codigoDebito}")
	public ResponseEntity<List<Pagamento>> filtarPagamentoCodigoDebito(@PathVariable Integer codigoDebito) {
		return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.findBycodigoDebito(codigoDebito));
	}
	
	@GetMapping("/filtrar/cpf_cnpj/{cpfCnpj}")
	public ResponseEntity<List<Pagamento>> filtarPagamentoCpfCnpj(@PathVariable String cpfCnpj) {
		return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.findBycpfCnpj(cpfCnpj));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizaPagamento(@PathVariable long id, @RequestBody Pagamento pagamento){
		Optional<Pagamento> pagamentoOptional = pagamentoService.listarPorId(id);
	     if(!pagamentoOptional.isPresent()){
		      	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pagamento não encontrado através do ID: " + id);
		}
	    if(!pagamentoOptional.get().getStatusPagamento().equals(StatusPagamentoEnum.PROCESSADO_SUCESSO)){
	    	 if(!pagamentoOptional.get().getStatusPagamento().equals(StatusPagamentoEnum.PROCESSADO_FALHA)){
	    		var pagamento2 = new Pagamento();
	    		BeanUtils.copyProperties(pagamento2, pagamentoOptional);
	    		pagamento2.setId(pagamentoOptional.get().getId());
	  	        pagamento2.setCodigoDebito(pagamentoOptional.get().getCodigoDebito());
	  	        pagamento2.setCpfCnpj(pagamentoOptional.get().getCpfCnpj());
	  	        pagamento2.setMetodoPagamento(pagamentoOptional.get().getMetodoPagamento());
	  	        pagamento2.setValorPagamento(pagamentoOptional.get().getValorPagamento());
	  	        pagamento2.setStatusPagamento(pagamento.getStatusPagamento());
	  	        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.salvar(pagamento2));
	    	 }else {
	    		var pagamento2 = new Pagamento();
	    		BeanUtils.copyProperties(pagamento2, pagamentoOptional);
		    	pagamento2.setId(pagamentoOptional.get().getId());
		  	    pagamento2.setCodigoDebito(pagamentoOptional.get().getCodigoDebito());
		  	    pagamento2.setCpfCnpj(pagamentoOptional.get().getCpfCnpj());
		  	    pagamento2.setMetodoPagamento(pagamentoOptional.get().getMetodoPagamento());
		  	    pagamento2.setValorPagamento(pagamentoOptional.get().getValorPagamento());
		  	    pagamento2.setStatusPagamento(StatusPagamentoEnum.PENDENTE_PROCESSAMENTO);
		  	    return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.salvar(pagamento2));
	    	 }
	     }
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pagamento processado com Sucesso não pode ser alterado.");
	}
			
  
	 public ResponseEntity<Object> pesquisarPagamento(@PathVariable long id, @RequestBody Pagamento pagamento){
		Optional<Pagamento> pagamentoOptional = pagamentoService.listarPorId(id);
		if(!pagamentoOptional.isPresent()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pagamento não encontrado através do ID: " + id);
		}
		return ResponseEntity.status(HttpStatus.OK).body(pagamentoOptional.get());
	 }
}
