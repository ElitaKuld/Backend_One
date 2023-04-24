package com.example.backend_one;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

//för att inte riskera att man krockar med portarna
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloWorldControllerTest {

    //Spring kommer att skapa en faktisk instans av denna kontroller
    @Autowired
    private HelloWorldController controller;

    //Vi testar att vår controller skapas i huvud taget
    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }


    //Vi testar hur controller svarar på den urlen den ska svara på

    // För att få värdet på vår port:
    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void helloWorld() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class)).contains("Hello World!");
    }

    @Test
    void holaMundo() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/es", String.class)).contains("Hola Mundo!");
    }
}