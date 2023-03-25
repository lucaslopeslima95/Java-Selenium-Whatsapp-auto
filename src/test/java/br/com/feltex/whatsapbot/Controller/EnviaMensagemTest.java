package br.com.feltex.whatsapbot.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;

class EnviaMensagemTest {
    private TestRestTemplate restTemplate = new TestRestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String URL = "http://localhost:8080/zap-zap";

    @Test
    void enviarMensagem() throws Exception {


    }
}