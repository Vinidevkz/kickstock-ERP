package com.erp.kicksotck.controllers;

import com.erp.kicksotck.dtos.FornecedorDTO;
import com.erp.kicksotck.dtos.LoginDTO;
import com.erp.kicksotck.dtos.RespostaFornecedorContratoDTO;
import com.erp.kicksotck.dtos.RespostaFornecedorSolicitacaoLoteDTO;
import com.erp.kicksotck.responsedtos.FornecedorResponseDTO;
import com.erp.kicksotck.services.ContratoService;
import com.erp.kicksotck.services.FornecedorService;
import com.erp.kicksotck.services.SolicitacoesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.AccountNotFoundException;
import java.util.UUID;

@RestController
@RequestMapping("/v1/fornecedor")
@RequiredArgsConstructor
public class FornecedorController {

    private final FornecedorService fornecedorService;
    private final ContratoService contratoService;
    private final SolicitacoesService solicitacoesService;

    //register
    @PostMapping("/auth/register")
    public ResponseEntity<FornecedorResponseDTO> registerFornecedor(@RequestBody @Valid FornecedorDTO fornecedorDTO){
        FornecedorResponseDTO fornecedorResponseDTO = fornecedorService.registerFornecedor(fornecedorDTO);

        return ResponseEntity.status(201).body(fornecedorResponseDTO);
    }
    //login
    @PostMapping("/auth/login")
    public ResponseEntity<FornecedorResponseDTO> loginFornecedor(@RequestBody @Valid LoginDTO loginDTO) throws AccountNotFoundException {
        FornecedorResponseDTO fornecedorResponseDTO = fornecedorService.loginFornecedor(loginDTO.email(), loginDTO.password());

        return ResponseEntity.ok().body(fornecedorResponseDTO);
    }


    //update
    //put
    //delete

    //aceitar/recusar solicitação de contrato
    @PutMapping("/devolutiva_requisicao_contrato")
    public ResponseEntity<HttpStatus> devolutivaRequisicaoContrato(@RequestBody @Valid RespostaFornecedorContratoDTO respostaFornecedorContratoDTO) throws AccountNotFoundException {

        HttpStatus statusDaResposta = fornecedorService.respostaRequisicaoContrato(respostaFornecedorContratoDTO);

        return ResponseEntity.status(statusDaResposta).build();
    }

    //aceitar/recursar solicitação de contrato
    @PutMapping("/devolutiva_solicitacao_compra_lote")
    public ResponseEntity<HttpStatus> devolutivaSolicitacaoCompra(@RequestBody @Valid RespostaFornecedorSolicitacaoLoteDTO respostaFornecedorSolicitacaoLoteDTO) throws AccountException {

        HttpStatus statusDaResposta = solicitacoesService.respostaSolicitacao(respostaFornecedorSolicitacaoLoteDTO);

        return ResponseEntity.status(statusDaResposta).build();
    }
}
