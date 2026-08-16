package com.erp.kicksotck.services;

import com.erp.kicksotck.entities.Lote;
import com.erp.kicksotck.repositories.LoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoteService {

    private final LoteRepository loteRepository;

    //get lotes de uma empresa
    public List<Lote> getLotesEmpresa(UUID idEmpresa){

        List<Lote> lotes = loteRepository.findAllByIdEmpresa(idEmpresa);

        return lotes;

    }

}
