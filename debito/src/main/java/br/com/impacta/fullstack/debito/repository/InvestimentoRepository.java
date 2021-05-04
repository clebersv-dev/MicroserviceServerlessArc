package br.com.impacta.fullstack.debito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.impacta.fullstack.debito.domain.Investimento;

@Repository
public interface InvestimentoRepository extends JpaRepository<Investimento, Long> {

}
