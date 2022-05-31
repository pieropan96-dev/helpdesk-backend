package com.pieropan.helpdesk.service;

import com.pieropan.helpdesk.dominio.Cliente;
import com.pieropan.helpdesk.dominio.Pessoa;
import com.pieropan.helpdesk.dominio.Tecnico;
import com.pieropan.helpdesk.dominio.dtos.ClienteDto;
import com.pieropan.helpdesk.repository.ClienteRepository;
import com.pieropan.helpdesk.repository.PessoaRepository;
import com.pieropan.helpdesk.service.exception.Dataintegrityviolationexception;
import com.pieropan.helpdesk.service.exception.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Cliente findById(Integer id) {
        return clienteRepository.findById(id).orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado."));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
    }

    public Cliente create(ClienteDto obj) {
        validaCampos(obj);
        Cliente cliente = new Cliente(obj);
        return clienteRepository.save(cliente);
    }

    private void validaCampos(ClienteDto obj) {
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

    public Cliente update(Integer id, ClienteDto clienteDto) {
        clienteDto.setId(id);
        Cliente cliente = findById(id);
        if (!cliente.getSenha().equals(clienteDto.getSenha()))
            clienteDto.setSenha(encoder.encode(clienteDto.getSenha()));
        validaCampos(clienteDto);
        Cliente tecnico = new Cliente(clienteDto);
        return clienteRepository.save(tecnico);
    }

    public void delete(Integer id) {
        Cliente tecnico = findById(id);
        if (!tecnico.getChamados().isEmpty()) {
            throw new Dataintegrityviolationexception("Ordem possui ordens de serviço e não pode ser deletado!");
        }
        clienteRepository.delete(tecnico);
    }
}