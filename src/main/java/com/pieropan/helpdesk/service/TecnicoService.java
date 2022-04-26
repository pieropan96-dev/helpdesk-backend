package com.pieropan.helpdesk.service;

import com.pieropan.helpdesk.dominio.Pessoa;
import com.pieropan.helpdesk.dominio.Tecnico;
import com.pieropan.helpdesk.dominio.dtos.TecnicoDto;
import com.pieropan.helpdesk.repository.PessoaRepository;
import com.pieropan.helpdesk.repository.TecnicoRepository;
import com.pieropan.helpdesk.service.exception.Dataintegrityviolationexception;
import com.pieropan.helpdesk.service.exception.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {
    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id) {
        return tecnicoRepository.findById(id).orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado."));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDto obj) {
        validaCampos(obj);
        Tecnico tecnico = new Tecnico(obj);
        return tecnicoRepository.save(tecnico);
    }

    private void validaCampos(TecnicoDto obj) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(obj.getCpf());
        if (pessoa.isPresent() && pessoa.get().getId() != obj.getId()) {
            throw new Dataintegrityviolationexception("Cpf já cadastrado no sistema.");
        }
        pessoa = pessoaRepository.findByEmail(obj.getEmail());
        if (pessoa.isPresent() && pessoa.get().getId() != obj.getId()) {
            throw new Dataintegrityviolationexception("E-mail já cadastrado no sistema.");
        }
    }

    public Tecnico update(Integer id, TecnicoDto tecnicoDto) {
        tecnicoDto.setId(id);
        validaCampos(tecnicoDto);
        Tecnico tecnico = new Tecnico(tecnicoDto);
        return tecnicoRepository.save(tecnico);
    }

    public void delete(Integer id) {
        Tecnico tecnico = findById(id);
        if (!tecnico.getChamados().isEmpty()) {
            throw new Dataintegrityviolationexception("Ordem possui ordens de serviço e não pode ser deletado!");
        }
        tecnicoRepository.delete(tecnico);
    }
}