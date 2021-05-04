package br.com.impacta.fullstack.conta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.impacta.fullstack.conta.domain.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

}
