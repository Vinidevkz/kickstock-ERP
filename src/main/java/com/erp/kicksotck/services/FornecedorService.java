package com.erp.kicksotck.services;

import com.erp.kicksotck.config.TokenProvider;
import com.erp.kicksotck.dtos.FornecedorDTO;
import com.erp.kicksotck.dtos.RespostaFornecedorContratoDTO;
import com.erp.kicksotck.dtos.RespostaFornecedorSolicitacaoLoteDTO;
import com.erp.kicksotck.entities.Fornecedor;
import com.erp.kicksotck.exceptions.BadCredentialsException;
import com.erp.kicksotck.repositories.FornecedorRepository;
import com.erp.kicksotck.responsedtos.FornecedorResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final ContratoService contratoService;
    private final SolicitacoesService solicitacoesService;

    //register
    public FornecedorResponseDTO registerFornecedor(FornecedorDTO fornecedorDTO){
        Fornecedor fornecedor = new Fornecedor();

        fornecedor.setNome_fornecedor(fornecedorDTO.nome_fornecedor());
        fornecedor.setEmail(fornecedorDTO.email_fornecedor());
        fornecedor.setCnpj_fornecedor(fornecedorDTO.cnpj_fornecedor());
        fornecedor.setCreated_at(LocalDateTime.now());

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

    //aceitar/recusar requisicao de contrato
    public HttpStatus respostaRequisicaoContrato(RespostaFornecedorContratoDTO respostaFornecedorContratoDTO) throws AccountNotFoundException {
        HttpStatus status = contratoService.respostaRequisicaoContrato(respostaFornecedorContratoDTO);

        return status;
    }

    //aceitar/recusar solicitacao de compra de lote
    public HttpStatus respostaSolicitacaoCompraLote(RespostaFornecedorSolicitacaoLoteDTO respostaFornecedorSolicitacaoLoteDTO) throws AccountException {
        HttpStatus status = solicitacoesService.respostaSolicitacao(respostaFornecedorSolicitacaoLoteDTO);

        return status;
    }
}
