package com.pieropan.helpdesk.service;

import com.pieropan.helpdesk.dominio.Pessoa;
import com.pieropan.helpdesk.repository.PessoaRepository;
import com.pieropan.helpdesk.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Pessoa> pessoa = pessoaRepository.findByEmail(email);
        if (pessoa.isPresent()) {
            return new UserSS(pessoa.get().getId(), pessoa.get().getEmail(),
                    pessoa.get().getSenha(), pessoa.get().getPerfis());
        }
        throw new UsernameNotFoundException(email);
    }
}
