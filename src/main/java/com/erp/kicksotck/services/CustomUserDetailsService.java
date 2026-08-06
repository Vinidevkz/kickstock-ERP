package com.erp.kicksotck.services;

import com.erp.kicksotck.repositories.EmpresaRepository;
import com.erp.kicksotck.repositories.FornecedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final EmpresaRepository empresaRepository;
    private final FornecedorRepository fornecedorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        var empresa = empresaRepository.findByEmail(email);
        if (empresa.isPresent()) {
            return empresa.get();
        }

        var fornecedor = fornecedorRepository.findByEmail(email);
        if (fornecedor.isPresent()) {
            return fornecedor.get();
        }

        throw new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email);
    }
}