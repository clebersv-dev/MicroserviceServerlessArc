package br.com.impacta.fullstack.debito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.impacta.fullstack.debito.domain.Debit;

@Repository
public interface DebitRepository extends JpaRepository<Debit, Integer> {

}
