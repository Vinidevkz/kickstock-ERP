package com.erp.kicksotck.services;

import com.erp.kicksotck.entities.Empresa;
import com.erp.kicksotck.entities.Fornecedor;
import com.erp.kicksotck.entities.Lote;
import com.erp.kicksotck.entities.Solicitacoes;
import com.erp.kicksotck.enums.Status;
import com.erp.kicksotck.enums.TipoSolicitacao;
import com.erp.kicksotck.repositories.EmpresaRepository;
import com.erp.kicksotck.repositories.FornecedorRepository;
import com.erp.kicksotck.repositories.SolicitacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SolicitacoesService {

    private final SolicitacaoRepository solicitacaoRepository;
    private final EmpresaRepository empresaRepository;
    private final FornecedorRepository fornecedorRepository;

    //criar solicitacao
    public void criarSolicitacao(UUID idEmpresa, UUID idFornecedor, String tipo_solicitacao, LocalDate data_compra) throws AccountException {

        Empresa empresa = empresaRepository.findById(idEmpresa).orElseThrow(AccountException::new);
        Fornecedor fornecedor = fornecedorRepository.findById(idFornecedor).orElseThrow(AccountException::new);

        Solicitacoes solicitacao = new Solicitacoes();
        solicitacao.setId_empresa(empresa);
        solicitacao.setId_fornecedor(fornecedor);
        solicitacao.setTipo_solicitacao(TipoSolicitacao.valueOf(tipo_solicitacao));
        solicitacao.setData_compra(data_compra);
        solicitacao.setStatus(Status.AGUARDANDO);
        solicitacao.setCreated_at(LocalDateTime.now());

        solicitacaoRepository.save(solicitacao);

    }

    public void aceitarSolicitacao(UUID id_solicitacao) throws AccountException {

        Solicitacoes solicitacao = solicitacaoRepository.findById(id_solicitacao).orElseThrow(AccountException::new);

        solicitacao.setStatus(Status.ACEITO);

        if(solicitacao.getLotes() != null && !solicitacao.getLotes().isEmpty()){
            for(Lote lote : solicitacao.getLotes()){
                lote.setId_empresa(solicitacao.getId_empresa());
            }
        }

        solicitacaoRepository.save(solicitacao);


    }


}
