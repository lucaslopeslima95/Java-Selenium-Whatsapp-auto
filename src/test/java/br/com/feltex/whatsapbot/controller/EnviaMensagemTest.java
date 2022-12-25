package br.com.feltex.whatsapbot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Set;

class EnviaMensagemTest {
    private TestRestTemplate restTemplate = new TestRestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String URL = "http://localhost:8080/zap-zap";

    @Test
    void enviarMensagem() throws Exception {


    }
}