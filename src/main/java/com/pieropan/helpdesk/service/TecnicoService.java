package com.pieropan.helpdesk.service;

import com.pieropan.helpdesk.dominio.Tecnico;
import com.pieropan.helpdesk.repository.TecnicoRepository;
import com.pieropan.helpdesk.service.exception.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TecnicoService {
    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id) {
        return tecnicoRepository.findById(id).orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado."));
    }
}