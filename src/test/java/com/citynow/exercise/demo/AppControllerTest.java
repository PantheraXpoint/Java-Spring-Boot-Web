package com.citynow.exercise.demo;

import com.citynow.exercise.demo.Entity.UserEntity;
import com.citynow.exercise.demo.Repository.CardRepository;
import com.citynow.exercise.demo.Repository.UserRepository;
import com.citynow.exercise.demo.Service.AppServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(AppController.class)
public class AppControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppServiceImpl service;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CardRepository cardRepository;

    @Test   
    public void testCreatedUser() throws JsonProcessingException, Exception{
        UserEntity testing = new UserEntity();
        testing.setEmail("q");
        testing.setPassword("d");
        UserEntity result = new UserEntity();
        result.setId(1);
        result.setEmail("q");
        result.setPassword("d");
        Mockito.when(service.save(testing)).thenReturn(result);
        String url = "/user/created";
        ObjectMapper om = new ObjectMapper();
        String jsonRequest = om.writeValueAsString(testing);

        mockMvc.perform(MockMvcRequestBuilders.post(url).content(jsonRequest).contentType("application/json")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string("quang"));

    }

    
}
