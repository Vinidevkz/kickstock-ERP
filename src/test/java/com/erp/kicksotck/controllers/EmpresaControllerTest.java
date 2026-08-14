package com.erp.kicksotck.controllers;

import com.erp.kicksotck.dtos.EmpresaDTO;
import com.erp.kicksotck.entities.Empresa;
import com.erp.kicksotck.responsedtos.EmpresaResponseDTO;
import com.erp.kicksotck.services.EmpresaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.postgresql.hostchooser.HostRequirement.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class EmpresaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmpresaService empresaService;

    @Test
    @DisplayName("Deve retornar o status 201 Created ao registrar uma nova empresa.")
    void deveRetornar201CreatedAoRegistrarUmaNovaEmpresa() throws Exception{

        //arrange
        EmpresaDTO empresaDTO = new EmpresaDTO(
                "nome_empresa_test",
                "emailempresatest@gmail.com",
                "empresa123test",
                "11.111.111/1111-11"
        );

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(empresaDTO);

        Empresa empresaMock = new Empresa();
        empresaMock.setNome_empresa("empresa_teste");

        EmpresaResponseDTO empresaResponseDTO = new EmpresaResponseDTO(empresaMock, "token_teste123");

        when(empresaService.registerCompany(any(EmpresaDTO.class))).thenReturn(empresaResponseDTO);

        mockMvc.perform(
                post("/v1/empresa/auth/register").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody)
        )
                .andExpect(status().isCreated());

    }

    @Test
    @WithMockUser
    @DisplayName("Deve retornar Status OK 200 ao buscar os contratos de uma empresa válida.")
    void deveRetornar200AoBuscarContratosDaEmpresa() throws Exception{

        //arrange (preparação)
        UUID idEmpresaTeste = UUID.randomUUID();

        //service simulado retorna lista vazia sem dar erro
        when(empresaService.getContratosEmpresa(idEmpresaTeste)).thenReturn(List.of());

        //act (ação) e assert (simular chamada http e validar a resposta)
        mockMvc.perform(
                        get("/v1/empresa/contrato/{idEmpresa}", idEmpresaTeste)
                                .with(user("usuarioTest").roles("USER"))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

}
