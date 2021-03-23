package br.com.impacta.fullstack.saldoextrato.repository;

import br.com.impacta.fullstack.saldoextrato.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}