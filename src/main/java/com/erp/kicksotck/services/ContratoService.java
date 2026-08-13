package com.erp.kicksotck.services;

import com.erp.kicksotck.entities.Contrato;
import com.erp.kicksotck.entities.Empresa;
import com.erp.kicksotck.entities.Fornecedor;
import com.erp.kicksotck.enums.Status;
import com.erp.kicksotck.repositories.ContratoRepository;
import com.erp.kicksotck.repositories.EmpresaRepository;
import com.erp.kicksotck.repositories.FornecedorRepository;
import com.erp.kicksotck.tools.CodeBase64Generator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContratoService {

    private final ContratoRepository contratoRepository;
    private final CodeBase64Generator codeBase64Generator;
    private final EmpresaRepository empresaRepository;
    private final FornecedorRepository fornecedorRepository;

    public ContratoService(ContratoRepository contratoRepository, CodeBase64Generator codigoGeradorTool, EmpresaRepository empresaRepository, FornecedorRepository fornecedorRepository) {
        this.contratoRepository = contratoRepository;
        this.codeBase64Generator = codigoGeradorTool;
        this.empresaRepository = empresaRepository;
        this.fornecedorRepository = fornecedorRepository;
    }


    //solicitação da empresa
    public void solicitacaoContrato(Empresa empresa, Fornecedor fornecedor, LocalDate data_encerramento) throws InstanceAlreadyExistsException {
        boolean existsByIdEmpresaAndIdFornecedor = contratoRepository.existsByEmpresaEFornecedor(empresa, fornecedor);

        if(existsByIdEmpresaAndIdFornecedor){
            throw new InstanceAlreadyExistsException("A empresa já possui um contrato em aberto com o respectivo fornecedor.");
        }

        Contrato contrato = new Contrato();
        contrato.setId_empresa(empresa);
        contrato.setId_fornecedor(fornecedor);
        contrato.setData_encerramento(data_encerramento);
        contrato.setStatus_contrato(Status.AGUARDANDO);
        contrato.setCodigo_contrato(codeBase64Generator.gerarCodigoContrato());
        contrato.setCreated_at(LocalDateTime.now());

        contratoRepository.save(contrato);
    }


    //solicitação do fornecedor
    public void aceitarSolicitacao(UUID idContrato) throws AccountNotFoundException {
        Contrato contrato = contratoRepository.findById(idContrato).orElseThrow(AccountNotFoundException::new);

        contrato.setStatus_contrato(Status.ACEITO);

        contratoRepository.save(contrato);
    }

    //verifica se a empresa já possui um contrato com o fornecedor
    public boolean verificarContratoExistente(Empresa empresa, Fornecedor fornecedor) throws AccountNotFoundException {
        Contrato contrato = contratoRepository.findByEmpresaEFornecedor(empresa, fornecedor).orElseThrow(AccountNotFoundException::new);

        if(contrato.getStatus_contrato() != Status.ACEITO){
            return false;
        }else{
            return true;
        }
    }

}
