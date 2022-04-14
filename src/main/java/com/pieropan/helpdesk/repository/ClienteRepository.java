package com.pieropan.helpdesk.repository;

import com.pieropan.helpdesk.dominio.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}