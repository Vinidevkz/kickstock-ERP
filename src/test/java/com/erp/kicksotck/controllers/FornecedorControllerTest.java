package com.erp.kicksotck.controllers;

import com.erp.kicksotck.dtos.RespostaFornecedorContratoDTO;
import com.erp.kicksotck.services.FornecedorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FornecedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    static ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    private FornecedorService fornecedorService;
    
    @Test
    @WithMockUser
    @DisplayName("Deve retornar um HttpStatus ACCEPTED ao aceitar um contrato")
    void deveRetornarUmHttpStatusAcceptedAoAceitarContrato() throws Exception{

        RespostaFornecedorContratoDTO respostaFornecedorContratoDTO = new RespostaFornecedorContratoDTO(UUID.randomUUID(), "ACEITO");
        
        when(fornecedorService.respostaRequisicaoContrato(respostaFornecedorContratoDTO)).thenReturn(HttpStatus.ACCEPTED);
        
        String jsonBody = objectMapper.writeValueAsString(respostaFornecedorContratoDTO);

        mockMvc.perform(
                put("/v1/fornecedor/devolutiva_requisicao_contrato")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody)
                        .with(user("usuarioTest").roles("USER"))
        ).andExpect(status().isAccepted());

    }

    @Test
    @WithMockUser
    @DisplayName("Deve retornar um HttpStatus NOT_ACCEPTABLE ao recusar um contrato")
    void deveRetornarUmHttpStatusNotAcceptableAoRecusarContrato() throws Exception{

        RespostaFornecedorContratoDTO respostaFornecedorContratoDTO = new RespostaFornecedorContratoDTO(UUID.randomUUID(), "RECUSADO");

        when(fornecedorService.respostaRequisicaoContrato(respostaFornecedorContratoDTO)).thenReturn(HttpStatus.NOT_ACCEPTABLE);

        String jsonBody = objectMapper.writeValueAsString(respostaFornecedorContratoDTO);

        mockMvc.perform(
                put("/v1/fornecedor/devolutiva_requisicao_contrato")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody)
                        .with(user("usuarioTest").roles("USER"))
        ).andExpect(status().isNotAcceptable());

    }

}
