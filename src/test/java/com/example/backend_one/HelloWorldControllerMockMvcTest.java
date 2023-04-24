package com.example.backend_one;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Lättviktig test
//Spring-kontext skapas upp, men ingen webbserver startas
@SpringBootTest
@AutoConfigureMockMvc
public class HelloWorldControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnHelloWorld() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello World!")));
    }

    @Test
    public void shouldReturnHolaMundo() throws Exception {
        this.mockMvc.perform(get("/es")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hola Mundo!")));
    }

    @Test
    public void shouldReturn404() throws Exception {
        this.mockMvc.perform(get("/nonsenseURL")).andDo(print())
                .andExpect(status().isNotFound());
    }
}
