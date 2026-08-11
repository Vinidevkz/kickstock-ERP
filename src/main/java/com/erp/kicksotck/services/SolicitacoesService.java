package com.erp.kicksotck.services;

import com.erp.kicksotck.repositories.SolicitacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SolicitacoesService {

    private final SolicitacaoRepository solicitacaoRepository;

    //criar solicitacao
    public void criarSolicitacao();


}
