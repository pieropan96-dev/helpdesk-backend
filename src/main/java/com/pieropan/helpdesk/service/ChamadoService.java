package com.pieropan.helpdesk.service;

import com.pieropan.helpdesk.dominio.Chamado;
import com.pieropan.helpdesk.dominio.Cliente;
import com.pieropan.helpdesk.dominio.Tecnico;
import com.pieropan.helpdesk.dominio.dtos.ChamadoDto;
import com.pieropan.helpdesk.dominio.enums.Prioridade;
import com.pieropan.helpdesk.dominio.enums.Status;
import com.pieropan.helpdesk.repository.ChamadoRepository;
import com.pieropan.helpdesk.service.exception.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;

    public Chamado findById(Integer id) {
        return chamadoRepository.findById(id).orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado."));
    }

    public List<Chamado> findAll() {
        return chamadoRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
    }

    public Chamado create(ChamadoDto chamadoDto) {
        return chamadoRepository.save(newChamado(chamadoDto));
    }

    public Chamado update(Integer id, ChamadoDto chamadoDto) {
        chamadoDto.setId(id);
        Chamado chamado = findById(id);
        chamado = newChamado(chamadoDto);
        return chamadoRepository.save(chamado);
    }

    private Chamado newChamado(ChamadoDto chamadoDto) {
        Tecnico tecnico = tecnicoService.findById(chamadoDto.getTecnico());
        Cliente cliente = clienteService.findById(chamadoDto.getCliente());

        Chamado chamado = new Chamado();
        if (Objects.nonNull(chamadoDto)) {
            chamado.setId(chamadoDto.getId());
        }

        if (chamadoDto.getStatus().equals(Status.ENCERRADO.getCodigo())) {
            chamado.setDataFechamento(LocalDate.now());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(chamadoDto.getPrioridade()));
        chamado.setStatus(Status.toEnum(chamadoDto.getStatus()));
        chamado.setTitulo(chamadoDto.getTitulo());
        chamado.setObservacoes(chamadoDto.getObservacoes());
        return chamado;
    }
}