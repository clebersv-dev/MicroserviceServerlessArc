package br.com.impacta.fullstack.debito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.impacta.fullstack.debito.domain.Cartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {

}
