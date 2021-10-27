package com.citynow.locker;

import com.citynow.locker.controller.AppController;
import com.citynow.locker.entity.newDto.newTicketDetailDTO;
import com.citynow.locker.repository.NewTicketRepoImpl;
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
public class ControllerLayerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NewTicketRepoImpl newTicketRepoImpl;

    @Test
    public void testUpdateTicket() throws JsonProcessingException, Exception {
        newTicketDetailDTO testing = new newTicketDetailDTO();
        testing.setFullName("quang");
        testing.setGender(0);
        testing.setPhoneNumber("0903415805");
        newTicketDetailDTO result = new newTicketDetailDTO();
        result.setRemark("not available");
        result.setFullName("quang");
        result.setGender(0);
        result.setPhoneNumber("0903415805");
        Mockito.when(newTicketRepoImpl.updateTicket(testing)).thenReturn(newTicketRepoImpl.updateTicket(result));
        String url = "ticket/update";
        ObjectMapper om = new ObjectMapper();
        String jsonRequest = om.writeValueAsString(testing);

        mockMvc.perform(MockMvcRequestBuilders.post(url).content(jsonRequest).contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("quang"));
    }
}
