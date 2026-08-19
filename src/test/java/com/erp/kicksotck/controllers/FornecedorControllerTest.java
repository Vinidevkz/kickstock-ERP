package com.erp.kicksotck.controllers;

import com.erp.kicksotck.services.FornecedorService;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class FornecedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    static ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    private FornecedorService fornecedorService;

}
