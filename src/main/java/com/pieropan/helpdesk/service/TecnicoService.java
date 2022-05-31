package com.pieropan.helpdesk.service;

import com.pieropan.helpdesk.dominio.Pessoa;
import com.pieropan.helpdesk.dominio.Tecnico;
import com.pieropan.helpdesk.dominio.dtos.TecnicoDto;
import com.pieropan.helpdesk.repository.PessoaRepository;
import com.pieropan.helpdesk.repository.TecnicoRepository;
import com.pieropan.helpdesk.service.exception.Dataintegrityviolationexception;
import com.pieropan.helpdesk.service.exception.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {
    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Tecnico findById(Integer id) {
        return tecnicoRepository.findById(id).orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado."));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
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
        obj.setSenha(encoder.encode(obj.getSenha()));
    }

    public Tecnico update(Integer id, TecnicoDto tecnicoDto) {
        tecnicoDto.setId(id);
        Tecnico tecnico = findById(id);
        if (!tecnico.getSenha().equals(tecnicoDto.getSenha()))
            tecnicoDto.setSenha(encoder.encode(tecnicoDto.getSenha()));
        validaCampos(tecnicoDto);
        tecnico = new Tecnico(tecnicoDto);
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