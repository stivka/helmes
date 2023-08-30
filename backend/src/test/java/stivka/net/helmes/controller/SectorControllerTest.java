package stivka.net.helmes.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import stivka.net.helmes.model.Sector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SectorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getPayloadForHtmlId342() throws Exception {
        Sector expectedResponse = new Sector();
        expectedResponse.setId(5);
        expectedResponse.setHtmlId(342);
        expectedResponse.setName("Bakery & confectionery products");
        expectedResponse.setDepthLevel(2);

        Sector parent1 = new Sector();
        parent1.setId(4);
        parent1.setHtmlId(6);
        parent1.setName("Food and Beverage");
        parent1.setDepthLevel(1);

        Sector parent2 = new Sector();
        parent2.setId(1);
        parent2.setHtmlId(1);
        parent2.setName("Manufacturing");
        parent2.setDepthLevel(0);

        parent1.setParent(parent2);
        expectedResponse.setParent(parent1);

        MvcResult result = mockMvc.perform(get("/api/sectors/342"))
                .andExpect(status().isOk())
                .andReturn();
        String actualResponseString = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        Sector actualResponse = objectMapper.readValue(actualResponseString, Sector.class);

        assertEquals(expectedResponse, actualResponse);
    }
}
