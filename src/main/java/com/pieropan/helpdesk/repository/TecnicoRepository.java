package com.pieropan.helpdesk.repository;

import com.pieropan.helpdesk.dominio.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
}