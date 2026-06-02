package fr.takima.training.simpleapi;

import fr.takima.training.simpleapi.controller.DepartmentController;
import fr.takima.training.simpleapi.controller.GreetingController;
import fr.takima.training.simpleapi.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({DepartmentController.class, GreetingController.class})
class ControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DepartmentRepository departmentRepository;

    @Test
    void shouldCallGreetingEndpoint() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCallDepartmentEndpoint() throws Exception {
        when(departmentRepository.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/departments"))
                .andExpect(status().isOk());
    }
}