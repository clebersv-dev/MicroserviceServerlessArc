package br.com.impacta.fullstack.saldoextrato.repository;

import br.com.impacta.fullstack.saldoextrato.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User findByEmail(String email);
}
