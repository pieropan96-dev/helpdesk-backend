package com.pieropan.helpdesk.service;

import com.pieropan.helpdesk.dominio.Chamado;
import com.pieropan.helpdesk.dominio.Cliente;
import com.pieropan.helpdesk.dominio.Tecnico;
import com.pieropan.helpdesk.dominio.enums.Perfil;
import com.pieropan.helpdesk.dominio.enums.Prioridade;
import com.pieropan.helpdesk.dominio.enums.Status;
import com.pieropan.helpdesk.repository.ChamadoRepository;
import com.pieropan.helpdesk.repository.ClienteRepository;
import com.pieropan.helpdesk.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepostiroy;

    @Autowired
    private ChamadoRepository chamadoRepository;

    public void instanciaDB() {
        Tecnico tecnico = new Tecnico(null, "Matheus Pieropan", "0192043698", "matheus.2015jf@hotmail.com", "123");
        tecnico.addPerfil(Perfil.ADMIN);
        tecnicoRepository.save(tecnico);

        Cliente cliente = new Cliente(null, "José Angusto", "23449456423", "joseaugusto@hotmail.com", "456");
        clienteRepostiroy.save(cliente);

        Chamado chamado = new Chamado(null, Prioridade.MEDIA, Status.ABERTO, "Chamado 01", "Primeiro chamado", tecnico, cliente);
        chamadoRepository.save(chamado);
    }
}