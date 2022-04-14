package com.pieropan.helpdesk.repository;

import com.pieropan.helpdesk.dominio.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
}