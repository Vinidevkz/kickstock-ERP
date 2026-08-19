package com.erp.kicksotck.controllers;

import com.erp.kicksotck.dtos.ContratoDTO;
import com.erp.kicksotck.dtos.EmpresaDTO;
import com.erp.kicksotck.dtos.LoginDTO;
import com.erp.kicksotck.dtos.SolicitacaoDTO;
import com.erp.kicksotck.entities.Empresa;
import com.erp.kicksotck.entities.Fornecedor;
import com.erp.kicksotck.enums.TipoSolicitacao;
import com.erp.kicksotck.responsedtos.EmpresaResponseDTO;
import com.erp.kicksotck.services.ContratoService;
import com.erp.kicksotck.services.EmpresaService;
import com.erp.kicksotck.services.SolicitacoesService;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class EmpresaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    static ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    private EmpresaService empresaService;

    @MockitoBean
    private SolicitacoesService solicitacoesService;

    //generate mock custom methods
    private EmpresaResponseDTO generateEmpresaResponseDTOMock(){

        Empresa empresaMock = new Empresa();
        empresaMock.setNome_empresa("empresa_teste");

        EmpresaResponseDTO empresaResponseDTO = new EmpresaResponseDTO(empresaMock, "token_teste123");

        return empresaResponseDTO;
    }

    private String generateEmpresaDTOJsonBody(){
        EmpresaDTO empresaDTO = new EmpresaDTO(
                "nome_empresa_test",
                "emailempresatest@gmail.com",
                "empresa123test",
                "11.111.111/1111-11"
        );


        String jsonBody = objectMapper.writeValueAsString(empresaDTO);

        return jsonBody;
    }

    private String generateLoginDTOJsonBody(String email, String password){
        LoginDTO loginDTO = new LoginDTO(
            email, password
        );

        String jsonBody = objectMapper.writeValueAsString(loginDTO);

        return jsonBody;
    }

    //register(/v1/empresa/auth/register)
    @Test
    @DisplayName("Deve retornar o status 201 Created ao registrar uma nova empresa.")
    void deveRetornar201CreatedAoRegistrarUmaNovaEmpresa() throws Exception{

        //arrange
        EmpresaResponseDTO empresaResponseDTO = generateEmpresaResponseDTOMock();
        String jsonBody = generateEmpresaDTOJsonBody();

        when(empresaService.registerCompany(any(EmpresaDTO.class))).thenReturn(empresaResponseDTO);

        mockMvc.perform(
                post("/v1/empresa/auth/register").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody)
        )
                .andExpect(status().isCreated());

    }

    //login(/v1/empresa/auth/login)
    @Test
    @DisplayName("Deve retornar 200 OK ao buscar os dados do usuário no banco.")
    void deveRetornar200OkAoBuscarOsDadosDoUsuarioNoBanco() throws Exception{

        //arrange
        EmpresaResponseDTO empresaResponseDTO = generateEmpresaResponseDTOMock();
        String email_empresa_teste = "emailempresatest@gmail.com";
        String password_empresa_teste = "passwordEmpresaTeste123";

        String jsonBody = generateLoginDTOJsonBody(email_empresa_teste, password_empresa_teste);

        when(empresaService.loginCompany(email_empresa_teste, password_empresa_teste)).thenReturn(empresaResponseDTO);

        mockMvc.perform(
                post("/v1/empresa/auth/login").contentType(MediaType.APPLICATION_JSON).content(jsonBody)
        ).andExpect(status().isOk());

    }

    //buscar contratos da empresa(/v1/empresa/contrato/{idEmpresa})
    @Test
    @WithMockUser
    @DisplayName("Deve retornar Status OK 200 ao buscar os contratos de uma empresa válida.")
    void deveRetornar200AoBuscarContratosDaEmpresa() throws Exception{

        //arrange (preparação)
        UUID id_empresa_teste = UUID.randomUUID();

        //service simulado retorna lista vazia sem dar erro
        when(empresaService.getContratosEmpresa(id_empresa_teste)).thenReturn(List.of());

        //act (ação) e assert (simular chamada http e validar a resposta)
        mockMvc.perform(
                        get("/v1/empresa/contrato/{idEmpresa}", id_empresa_teste)
                                .with(user("usuarioTest").roles("USER"))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    //buscar lotes da empresa(/v1/empresa/lotes/{idEmpresa})
    @Test
    @WithMockUser
    @DisplayName("Deve retornr Status OK 200 ao buscar os lotes de uma empresa válida.")
    void deveRetornar200AoBuscarLotesDaEmpresa() throws Exception{

        UUID id_empresa_teste = UUID.randomUUID();

        when(empresaService.getLotesEmpresa(id_empresa_teste)).thenReturn(List.of());

        mockMvc.perform(
                get("/v1/empresa/lotes/{idEmpresa}", id_empresa_teste)
                        .with(user("usuarioTest").roles("USER"))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

    }

    //requisição de contrato(/)
    @Test
    @WithMockUser
    @DisplayName("Deve retornar 201 CREATED ao criar uma requisição de contrato a um fornecedor.")
    void deveRetornar201CreatedAoCriarUmaRequisicaoDeContrato() throws Exception{

        ContratoDTO contratoDTO = new ContratoDTO(UUID.randomUUID(), LocalDate.now());

        String jsonBody = objectMapper.writeValueAsString(contratoDTO);

        mockMvc.perform(
                post("/v1/empresa/requisicao_de_contrato")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody)
                        .with(user("usuarioTest").roles("USER"))
        ).andExpect(status().isCreated());

    }

    //solicitar compra de lote(/)
    @Test
    @WithMockUser
    @DisplayName("Deve retornar 201 CREATED ao criar uma solicitação de compra de lote")
    void deveRetornar201CreatedAoCriarUmaSolicitacaoDeCompraDeLote() throws Exception{

        SolicitacaoDTO solicitacaoDTO = new SolicitacaoDTO(
                UUID.randomUUID(),
                UUID.randomUUID(),
                "COMPRA",
                1,
                List.of(UUID.randomUUID()),
                LocalDate.now()
        );

        String jsonBody = objectMapper.writeValueAsString(solicitacaoDTO);

        mockMvc.perform(
                post("/v1/empresa/solicitacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody)
                        .with(user("usuarioTest").roles("USER"))
        ).andExpect(status().isCreated());

    }
}
