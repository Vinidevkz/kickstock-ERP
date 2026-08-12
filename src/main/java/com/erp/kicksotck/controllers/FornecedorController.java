package com.erp.kicksotck.controllers;

import com.erp.kicksotck.dtos.FornecedorDTO;
import com.erp.kicksotck.dtos.LoginDTO;
import com.erp.kicksotck.responsedtos.FornecedorResponseDTO;
import com.erp.kicksotck.services.ContratoService;
import com.erp.kicksotck.services.FornecedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.UUID;

@RestController
@RequestMapping("/v1/fornecedor")
@RequiredArgsConstructor
public class FornecedorController {

    private final FornecedorService fornecedorService;
    private final ContratoService contratoService;

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

    //aceitar solicitação de contrato
    @PutMapping("/aceitar_solicitacao_contrato/{idContrato}")
    public ResponseEntity<Void> aceitarContrato(@PathVariable("idContrato") UUID idContrado) throws AccountNotFoundException {
        contratoService.aceitarSolicitacao(idContrado);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    //aceitar
}
