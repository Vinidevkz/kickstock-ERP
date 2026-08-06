package com.erp.kicksotck.controllers;

import com.erp.kicksotck.dtos.FornecedorDTO;
import com.erp.kicksotck.responsedtos.FornecedorResponseDTO;
import com.erp.kicksotck.services.FornecedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fornecedor")
@RequiredArgsConstructor
public class FornecedorController {

    private final FornecedorService fornecedorService;

    //register
    @PostMapping("/register")
    public ResponseEntity<FornecedorResponseDTO> registerFornecedor(@RequestBody @Valid FornecedorDTO fornecedorDTO){
        FornecedorResponseDTO fornecedorResponseDTO = fornecedorService.registerFornecedor(fornecedorDTO);

        return ResponseEntity.status(201).body(fornecedorResponseDTO);
    }
    //login
    //update
    //put
    //delete
}
