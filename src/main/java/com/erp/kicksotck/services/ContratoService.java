package com.erp.kicksotck.services;

import com.erp.kicksotck.entities.Contrato;
import com.erp.kicksotck.entities.Empresa;
import com.erp.kicksotck.entities.Fornecedor;
import com.erp.kicksotck.enums.Status;
import com.erp.kicksotck.repositories.ContratoRepository;
import com.erp.kicksotck.tools.CodeBase64Generator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.UUID;

@Service
public class ContratoService {

    private final ContratoRepository contratoRepository;
    private final CodeBase64Generator codeBase64Generator;

    public ContratoService(ContratoRepository contratoRepository, CodeBase64Generator codigoGeradorTool) {
        this.contratoRepository = contratoRepository;
        this.codeBase64Generator = codigoGeradorTool;
    }


    //solicitação da empresa
    public void solicitacaoContrato(Empresa empresa, Fornecedor fornecedor){
        Contrato contrato = new Contrato();
        contrato.setId_empresa(empresa);
        contrato.setId_fornecedor(fornecedor);
    }


    //solicitação do fornecedor
    public void aceitarSolicitacao(UUID idContrato) throws AccountNotFoundException {
        Contrato contrato = contratoRepository.findById(idContrato).orElseThrow(AccountNotFoundException::new);

        contrato.setStatus_contrato(Status.ACEITO);

        contratoRepository.save(contrato);
    }

}
