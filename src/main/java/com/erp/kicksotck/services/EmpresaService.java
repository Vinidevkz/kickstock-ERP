package com.erp.kicksotck.services;

import com.erp.kicksotck.config.TokenProvider;
import com.erp.kicksotck.dtos.EmpresaDTO;
import com.erp.kicksotck.entities.Empresa;
import com.erp.kicksotck.entities.Fornecedor;
import com.erp.kicksotck.repositories.EmpresaRepository;
import com.erp.kicksotck.repositories.FornecedorRepository;
import com.erp.kicksotck.responsedtos.EmpresaResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.erp.kicksotck.exceptions.BadCredentialsException;

import javax.management.InstanceAlreadyExistsException;
import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final ContratoService contratoService;
    private final FornecedorRepository fornecedorRepository;

    //register
    public EmpresaResponseDTO registerCompany(EmpresaDTO empresaDTO){

        Empresa newEmpresa=  new Empresa();
        newEmpresa.setNome_empresa(empresaDTO.nome_empresa());
        newEmpresa.setEmail(empresaDTO.email_empresa());
        newEmpresa.setCnpj_empresa(empresaDTO.cnpj_empresa());
        newEmpresa.setCreated_at(LocalDateTime.now());

        //email is subject
        String token = tokenProvider.buildToken(empresaDTO.email_empresa());
        newEmpresa.setPassword_empresa(passwordEncoder.encode(empresaDTO.password_empresa()));

        Empresa empresa = empresaRepository.save(newEmpresa);

        return new EmpresaResponseDTO(empresa, token);
    }

    //login
    public EmpresaResponseDTO loginCompany(String email, String password) throws AccountNotFoundException {
        Empresa empresa = empresaRepository.findByEmail(email).orElseThrow(BadCredentialsException::new);

        boolean isPasswordValid = passwordEncoder.matches(password, empresa.getPassword_empresa());

        if(!isPasswordValid){
            throw new BadCredentialsException();
        }

        String token = tokenProvider.buildToken(empresa.getEmail());

        return new EmpresaResponseDTO(empresa, token);
    }
    //update
    //put
    //delete

    //requisição de contrato
    public void solicitarContrato(String emailEmpresa, UUID idFornecedor, LocalDate data_encerramento) throws AccountNotFoundException, InstanceAlreadyExistsException {

        Empresa empresa = empresaRepository.findByEmail(emailEmpresa)
                .orElseThrow(() -> new AccountNotFoundException("Empresa não encontrada para o e-mail: " + emailEmpresa));

        Fornecedor fornecedor = fornecedorRepository.findById(idFornecedor)
                .orElseThrow(() -> new AccountNotFoundException("Fornecedor não encontrado para o ID: " + idFornecedor));

        contratoService.solicitacaoContrato(empresa, fornecedor, data_encerramento);
    }



}
