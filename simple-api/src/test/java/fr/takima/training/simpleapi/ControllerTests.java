package fr.takima.training.simpleapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    void departmentEndpointWorks() throws Exception {

        mockMvc.perform(get("/departments"))
                .andExpect(status().isOk());

    }

    @Test
    void studentEndpointWorks() throws Exception {

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk());

    }

}