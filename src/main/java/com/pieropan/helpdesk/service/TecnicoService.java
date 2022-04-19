package com.pieropan.helpdesk.service;

import com.pieropan.helpdesk.dominio.Tecnico;
import com.pieropan.helpdesk.dominio.dtos.TecnicoDto;
import com.pieropan.helpdesk.repository.TecnicoRepository;
import com.pieropan.helpdesk.service.exception.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnicoService {
    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id) {
        return tecnicoRepository.findById(id).orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado."));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDto obj) {
        obj.setId(null);
        Tecnico tecnico = new Tecnico(obj);
        return tecnicoRepository.save(tecnico);
    }
}