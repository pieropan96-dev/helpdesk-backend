package com.pieropan.helpdesk.service;

import com.pieropan.helpdesk.dominio.Chamado;
import com.pieropan.helpdesk.repository.ChamadoRepository;
import com.pieropan.helpdesk.service.exception.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    public Chamado findById(Integer id) {
        return chamadoRepository.findById(id).orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado."));
    }

    public List<Chamado> findAll() {
        return chamadoRepository.findAll();
    }
}