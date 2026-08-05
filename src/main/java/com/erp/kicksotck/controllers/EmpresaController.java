package com.erp.kicksotck.controllers;

import com.erp.kicksotck.dtos.EmpresaDTO;
import com.erp.kicksotck.dtos.LoginDTO;
import com.erp.kicksotck.responsedtos.EmpresaResponseDTO;
import com.erp.kicksotck.services.EmpresaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.security.auth.login.AccountNotFoundException;
import java.net.URI;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaService empresaService;

    //register
    @PostMapping("/register")
    public ResponseEntity<EmpresaResponseDTO> registerCompany(@RequestBody @Valid EmpresaDTO empresaDTO){
        EmpresaResponseDTO newEmpresa = empresaService.registerCompany(empresaDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newEmpresa.id())
                .toUri();

        return ResponseEntity.created(uri).body(newEmpresa);
    }

    //login
    @PostMapping("/login")
    public ResponseEntity<EmpresaResponseDTO> loginCompany(@RequestBody @Valid LoginDTO loginDTO) throws AccountNotFoundException {
        EmpresaResponseDTO empresaResponseDTO = empresaService.loginCompany(loginDTO.email(), loginDTO.password());

        return ResponseEntity.ok().body(empresaResponseDTO);
    }

    //update
    //put
    //delete
}
