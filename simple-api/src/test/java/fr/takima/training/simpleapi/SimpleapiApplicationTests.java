package fr.takima.training.simpleapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import fr.takima.training.simpleapi.entity.Department;
import fr.takima.training.simpleapi.entity.Student;
import fr.takima.training.simpleapi.repository.DepartmentRepository;
import fr.takima.training.simpleapi.repository.StudentRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SimpleapiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void contextLoads() {
        assertThat(mockMvc).isNotNull();
        assertThat(departmentRepository).isNotNull();
        assertThat(studentRepository).isNotNull();
    }

    @Test
    void shouldReturnDepartments() throws Exception {
        mockMvc.perform(get("/departments"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldReturnStudents() throws Exception {
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldSaveAndFindDepartment() {
        Department department = new Department();
        department.setName("Computer Science");

        Department savedDepartment = departmentRepository.save(department);

        assertThat(savedDepartment.getId()).isNotNull();
        assertThat(departmentRepository.findById(savedDepartment.getId())).isPresent();
        assertThat(departmentRepository.findById(savedDepartment.getId()).get().getName())
                .isEqualTo("Computer Science");
    }

    @Test
    void shouldSaveAndFindStudent() {
        Department department = new Department();
        department.setName("Engineering");
        Department savedDepartment = departmentRepository.save(department);

        Student student = new Student();
        student.setFirstname("John");
        student.setLastname("Doe");
        student.setDepartment(savedDepartment);

        Student savedStudent = studentRepository.save(student);

        assertThat(savedStudent.getId()).isNotNull();
        assertThat(studentRepository.findById(savedStudent.getId())).isPresent();
        assertThat(studentRepository.findById(savedStudent.getId()).get().getFirstname())
                .isEqualTo("John");
    }
}