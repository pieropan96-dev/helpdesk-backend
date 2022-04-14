package com.pieropan.helpdesk.repository;

import com.pieropan.helpdesk.dominio.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}