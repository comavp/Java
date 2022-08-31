package ru.comavp.backend.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.comavp.backend.contollers.RequestsCounterController;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(RequestsCounterController.class)
public class RequestsCounterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void firstRequest_one() throws Exception {
        mockMvc.perform(get("/requests"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }
}
