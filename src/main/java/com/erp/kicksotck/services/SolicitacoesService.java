package com.erp.kicksotck.services;

import com.erp.kicksotck.dtos.RespostaFornecedorSolicitacaoLoteDTO;
import com.erp.kicksotck.dtos.SolicitacaoDTO;
import com.erp.kicksotck.entities.Empresa;
import com.erp.kicksotck.entities.Fornecedor;
import com.erp.kicksotck.entities.Lote;
import com.erp.kicksotck.entities.Solicitacoes;
import com.erp.kicksotck.enums.Status;
import com.erp.kicksotck.enums.TipoSolicitacao;
import com.erp.kicksotck.repositories.EmpresaRepository;
import com.erp.kicksotck.repositories.FornecedorRepository;
import com.erp.kicksotck.repositories.LoteRepository;
import com.erp.kicksotck.repositories.SolicitacaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountException;
import java.nio.file.FileAlreadyExistsException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SolicitacoesService {

    private final SolicitacaoRepository solicitacaoRepository;
    private final EmpresaRepository empresaRepository;
    private final FornecedorRepository fornecedorRepository;
    private final LoteRepository loteRepository;
    private final ContratoService contratoService;

    //criar solicitacao
    @Transactional
    public void criarSolicitacao(SolicitacaoDTO solicitacaoDTO) throws AccountException, FileAlreadyExistsException {

        Empresa empresa = empresaRepository.findById(solicitacaoDTO.idEmpresa()).orElseThrow(AccountException::new);
        Fornecedor fornecedor = fornecedorRepository.findById(solicitacaoDTO.idFornecedor()).orElseThrow(AccountException::new);

        if(!contratoService.verificarContratoExistente(empresa, fornecedor)){
            throw new FileAlreadyExistsException("Contrato ainda em análise. Solicitações só são possíveis com contratos já em vigor.");
        }

        Solicitacoes solicitacao = new Solicitacoes();
        solicitacao.setId_empresa(empresa);
        solicitacao.setId_fornecedor(fornecedor);
        solicitacao.setTipo_solicitacao(TipoSolicitacao.valueOf(solicitacaoDTO.tipo_solicitacao()));
        solicitacao.setData_compra(solicitacaoDTO.data_compra());
        solicitacao.setStatus(Status.valueOf("AGUARDANDO"));
        solicitacao.setCreated_at(LocalDateTime.now());
        solicitacao.setQuantidade_lote(solicitacaoDTO.quantidade_lote());

        if(solicitacaoDTO.idsLotes() != null && !solicitacaoDTO.idsLotes().isEmpty()){
            List<Lote> lotes = loteRepository.findAllById(solicitacaoDTO.idsLotes());

            if(lotes.size() != solicitacaoDTO.idsLotes().size()){
                throw new IllegalArgumentException("Um dos lotes informados não existe no sistema.");
            }

            solicitacao.setLotes(lotes);
        }

        solicitacaoRepository.save(solicitacao);

    }

    public HttpStatus respostaSolicitacao(RespostaFornecedorSolicitacaoLoteDTO respostaFornecedorSolicitacaoLoteDTO) throws AccountException {

        Solicitacoes solicitacao = solicitacaoRepository.findById(respostaFornecedorSolicitacaoLoteDTO.id_solicitacao()).orElseThrow(AccountException::new);

        if(respostaFornecedorSolicitacaoLoteDTO.tipo_aceitacao() == "RECUSADO"){
            solicitacao.setStatus(Status.RECUSADO);

            return HttpStatus.NOT_ACCEPTABLE;
        }

        if(solicitacao.getLotes() != null && !solicitacao.getLotes().isEmpty()){
            for(Lote lote : solicitacao.getLotes()){
                lote.setId_empresa(solicitacao.getId_empresa());
            }
        }

        solicitacao.setStatus(Status.ACEITO);

        solicitacaoRepository.save(solicitacao);

        return HttpStatus.ACCEPTED;

    }


}
