package com.rangel.ibsc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rangel.ibsc.model.Client;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class ClientControllerUnitTest {

    @Autowired
    ClientController clientController;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getAllClients() throws Exception {
        //cria novos clientes
        Date data = new SimpleDateFormat("dd/MM/yyyy").parse("12/07/2021");
        Client sentClient = new Client(1,"Rangel",true,200.00,1,data);

        data = new SimpleDateFormat("dd/MM/yyyy").parse("25/02/1990");
        Client sentClient2 = new Client(2,"Rangel2",false,450.5,2,data);


        //guarda a resposta do cliente criado
        Client newClient = clientController.createClient(sentClient).getBody();
        Client newClient2 = clientController.createClient(sentClient2).getBody();

        //coloca o cliente esperado numa lista
        List<Client> sentClientList = new ArrayList<>();
        sentClientList.add(newClient);
        sentClientList.add(newClient2);

        //pega a lista que é mandada pelo controller
        List<Client> receivedClientList = clientController.getAllClient().getBody();

        assertEquals(objectMapper.writeValueAsString(sentClientList), objectMapper.writeValueAsString(receivedClientList));

    }


    @Test
    void createAndReturnClient() throws Exception {

        Date data = new SimpleDateFormat("dd/MM/yyyy").parse("12/07/2021");
        Client sentClient = new Client(1,"Rangel",true,200.00,1,data);

        Client newClient = clientController.createClient(sentClient).getBody();

        assertEquals(objectMapper.writeValueAsString(sentClient), objectMapper.writeValueAsString(newClient));

    }



}