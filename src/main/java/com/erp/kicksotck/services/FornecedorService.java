package com.erp.kicksotck.services;

import com.erp.kicksotck.config.TokenProvider;
import com.erp.kicksotck.dtos.FornecedorDTO;
import com.erp.kicksotck.entities.Fornecedor;
import com.erp.kicksotck.exceptions.BadCredentialsException;
import com.erp.kicksotck.repositories.FornecedorRepository;
import com.erp.kicksotck.responsedtos.FornecedorResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    //register
    public FornecedorResponseDTO registerFornecedor(FornecedorDTO fornecedorDTO){
        Fornecedor fornecedor = new Fornecedor();

        fornecedor.setNome_fornecedor(fornecedorDTO.nome_fornecedor());
        fornecedor.setEmail(fornecedorDTO.email_fornecedor());
        fornecedor.setCnpj_fornecedor(fornecedorDTO.cnpj_fornecedor());
        //email is subject
        String token = tokenProvider.buildToken(fornecedorDTO.email_fornecedor());
        String password = passwordEncoder.encode(fornecedorDTO.password_fornecedor());

        fornecedor.setPassword_fornecedor(password);

        Fornecedor newFornecedor = fornecedorRepository.save(fornecedor);

        return new FornecedorResponseDTO(newFornecedor, token);
    }

    //login
    public FornecedorResponseDTO loginFornecedor(String email, String password) throws AccountNotFoundException {
        Fornecedor fornecedor = fornecedorRepository.findByEmail(email).orElseThrow(BadCredentialsException::new);

        Boolean isPasswordValid = passwordEncoder.matches(password, fornecedor.getPassword_fornecedor());

        if(!isPasswordValid){
            throw new BadCredentialsException();
        }

        String token = tokenProvider.buildToken(fornecedor.getEmail());

        return new FornecedorResponseDTO(fornecedor, token);
    }
    //update
    //put
    //delete
}
