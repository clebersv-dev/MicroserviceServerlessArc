package br.com.impacta.fullstack.credito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.impacta.fullstack.credito.domain.Investimento;

@Repository
public interface InvestimentoRepository extends JpaRepository<Investimento, Long> {

}
