package fr.takima.training.simpleapi;

import fr.takima.training.simpleapi.entity.Department;
import fr.takima.training.simpleapi.entity.Student;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EntityTests {

    @Test
    void departmentEntityTest() {

        Department d = new Department();

        d.setName("Engineering");

        assertThat(d.getName())
                .isEqualTo("Engineering");
    }

    @Test
    void studentEntityTest() {

        Student s = new Student();

        s.setFirstname("John");
        s.setLastname("Doe");

        assertThat(s.getFirstname())
                .isEqualTo("John");

        assertThat(s.getLastname())
                .isEqualTo("Doe");
    }
}