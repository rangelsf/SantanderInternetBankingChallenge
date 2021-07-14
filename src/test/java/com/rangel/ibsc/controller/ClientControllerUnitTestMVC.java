package com.rangel.ibsc.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rangel.ibsc.model.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerUnitTestMVC {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void getAllClients() throws Exception {
        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk());

    }

    @Test
    void createAndReturnClientMVC() throws Exception {
        Date data = new SimpleDateFormat("dd/MM/yyyy").parse("12/07/2021");
        Client sentClient = new Client(1,"Rangel",true,200.00,1,data);

        mockMvc.perform(post("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sentClient)))
                .andExpect(status().isOk());
    }

}