package com.fadesp.teste.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fadesp.teste.domain.model.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long>{

	List<Pagamento> findBycodigoDebito(Integer codigoDebito);
	List<Pagamento> findBycpfCnpj(String cpfCnpj);
}


