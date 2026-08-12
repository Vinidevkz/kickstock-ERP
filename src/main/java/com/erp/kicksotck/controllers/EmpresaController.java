package com.erp.kicksotck.controllers;

import com.erp.kicksotck.dtos.ContratoDTO;
import com.erp.kicksotck.dtos.EmpresaDTO;
import com.erp.kicksotck.dtos.LoginDTO;
import com.erp.kicksotck.dtos.SolicitacaoDTO;
import com.erp.kicksotck.entities.Empresa;
import com.erp.kicksotck.responsedtos.EmpresaResponseDTO;
import com.erp.kicksotck.services.ContratoService;
import com.erp.kicksotck.services.EmpresaService;
import com.erp.kicksotck.services.SolicitacoesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.security.auth.login.AccountNotFoundException;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/v1/empresa")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaService empresaService;
    private final SolicitacoesService solicitacoesService;

    //register
    @PostMapping("/auth/register")
    public ResponseEntity<EmpresaResponseDTO> registerCompany(@RequestBody @Valid EmpresaDTO empresaDTO){
        EmpresaResponseDTO newEmpresa = empresaService.registerCompany(empresaDTO);

        return ResponseEntity.status(201).body(newEmpresa);
    }

    //login
    @PostMapping("/auth/login")
    public ResponseEntity<EmpresaResponseDTO> loginCompany(@RequestBody @Valid LoginDTO loginDTO) throws AccountNotFoundException {
        EmpresaResponseDTO empresaResponseDTO = empresaService.loginCompany(loginDTO.email(), loginDTO.password());

        return ResponseEntity.ok().body(empresaResponseDTO);
    }

    //update
    //put
    //delete

    //requisição de contrato
    @PostMapping("/requisicao_de_contrato")
    public ResponseEntity<String> requisitarContrato(@RequestBody @Valid ContratoDTO contratoDTO, Authentication authentication) throws AccountNotFoundException {

        String emailEmpresa = authentication.getName();

        System.out.println("Buscando empresa com o email: [" + emailEmpresa + "]");

        empresaService.solicitarContrato(emailEmpresa, contratoDTO.id_fornecedor(), contratoDTO.data_encerramento());

        return ResponseEntity.ok().body("Solicitação de contrato criada com sucesso. Aguarde a resposta do fornecedor.");

    }

    //requisicão de lote (fazer solicitação)
    @PostMapping("/solicitacao")
    public ResponseEntity<Void> solicitarLote(@RequestBody @Valid SolicitacaoDTO solicitacaoDTO, Authentication authentication){

        solicitacoesService.criarSolicitacao(solicitacaoDTO.idEmpresa(), solicitacaoDTO.idFornecedor(), solicitacaoDTO.tipo_solicitacao(), solicitacaoDTO.data_compra());

    }
}
