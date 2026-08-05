package com.erp.kicksotck.services;

import com.erp.kicksotck.config.TokenProvider;
import com.erp.kicksotck.dtos.EmpresaDTO;
import com.erp.kicksotck.entities.Empresa;
import com.erp.kicksotck.repositories.EmpresaRepository;
import com.erp.kicksotck.responsedtos.EmpresaResponseDTO;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.Token;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    //register
    public EmpresaResponseDTO registerCompany(EmpresaDTO empresaDTO){

        Empresa newEmpresa=  new Empresa();
        newEmpresa.setNome_empresa(empresaDTO.nome_empresa());
        newEmpresa.setEmail_empresa(empresaDTO.email_empresa());
        newEmpresa.setCnpj_empresa(empresaDTO.cnpj_empresa());
        //email is subject
        String token = tokenProvider.buildToken(empresaDTO.email_empresa());

        newEmpresa.setPassword_empresa(passwordEncoder.encode(empresaDTO.password_empresa()));

        Empresa empresa = empresaRepository.save(newEmpresa);

        return new EmpresaResponseDTO(empresa, token);
    }

    //login
    public EmpresaResponseDTO loginCompany(String email, String password) throws AccountNotFoundException {
        Optional<Empresa> opEmpresa = empresaRepository.findByEmail_empresa(email);

        if(opEmpresa.isEmpty()){
            throw new AccountNotFoundException("Empresa são encontrada.");
        }

        Empresa empresa = opEmpresa.get();

        boolean isPasswordValid = passwordEncoder.matches(password, empresa.getPassword_empresa());

        if(!isPasswordValid){
            throw new BadCredentialsException("Senha inválida.");
        }

        String token = tokenProvider.buildToken(empresa.getEmail_empresa());

        return new EmpresaResponseDTO(empresa.getId(), empresa.getNome_empresa(), empresa.getCnpj_empresa(), empresa.getEmail_empresa(), token, empresa.getCreated_at());
    }


    //update

    //put

    //delete



}
