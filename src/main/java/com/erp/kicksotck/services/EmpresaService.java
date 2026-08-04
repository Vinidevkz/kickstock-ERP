package com.erp.kicksotck.services;

import com.erp.kicksotck.config.TokenProvider;
import com.erp.kicksotck.dtos.EmpresaDTO;
import com.erp.kicksotck.entities.Empresa;
import com.erp.kicksotck.repositories.EmpresaRepository;
import com.erp.kicksotck.responsedtos.EmpresaResponseDTO;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.Token;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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


    //update

    //put

    //delete



}
