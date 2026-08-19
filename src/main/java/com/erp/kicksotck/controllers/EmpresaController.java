package com.erp.kicksotck.controllers;

import com.erp.kicksotck.dtos.ContratoDTO;
import com.erp.kicksotck.dtos.EmpresaDTO;
import com.erp.kicksotck.dtos.LoginDTO;
import com.erp.kicksotck.dtos.SolicitacaoDTO;
import com.erp.kicksotck.entities.Contrato;
import com.erp.kicksotck.entities.Empresa;
import com.erp.kicksotck.entities.Lote;
import com.erp.kicksotck.responsedtos.EmpresaResponseDTO;
import com.erp.kicksotck.services.ContratoService;
import com.erp.kicksotck.services.EmpresaService;
import com.erp.kicksotck.services.LoteService;
import com.erp.kicksotck.services.SolicitacoesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.management.InstanceAlreadyExistsException;
import javax.security.auth.login.AccountException;
import javax.security.auth.login.AccountNotFoundException;
import java.net.URI;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;
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
    public ResponseEntity<HttpStatus> requisitarContrato(@RequestBody @Valid ContratoDTO contratoDTO, Authentication authentication) throws AccountNotFoundException, InstanceAlreadyExistsException {

        String emailEmpresa = authentication.getName();

        empresaService.solicitarContrato(emailEmpresa, contratoDTO.id_fornecedor(), contratoDTO.data_encerramento());

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    //requisicão de lote (fazer solicitação)
    @PostMapping("/solicitacao")
    public ResponseEntity<Void> solicitarLote(@RequestBody @Valid SolicitacaoDTO solicitacaoDTO) throws AccountException, FileAlreadyExistsException {
        solicitacoesService.criarSolicitacao(solicitacaoDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //pegar contratos com fornecedores
    @GetMapping("/contrato/{idEmpresa}")
    public ResponseEntity<List<Contrato>> getContrato(@PathVariable("idEmpresa") UUID idEmpresa) throws AccountNotFoundException {
        List<Contrato> contratos = empresaService.getContratosEmpresa(idEmpresa);

        return ResponseEntity.ok().body(contratos);
    }

    //listar lotes da empresa
    @GetMapping("/lotes/{idEmpresa}")
    public ResponseEntity<List<Lote>> getLotes(@PathVariable("idEmpresa") UUID idEmpresa) throws AccountNotFoundException{
        List<Lote> lotes = empresaService.getLotesEmpresa(idEmpresa);

        return ResponseEntity.ok().body(lotes);
    }

    //listar solicitacoes da empresa


}
