package br.com.impacta.fullstack.credito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.impacta.fullstack.credito.domain.Fatura;
import br.com.impacta.fullstack.credito.domain.Fatura;

@Repository
public interface FaturaRepository extends JpaRepository<Fatura, Long> {

}
