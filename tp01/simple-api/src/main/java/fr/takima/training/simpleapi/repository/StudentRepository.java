package fr.takima.training.simpleapi.repository;

import fr.takima.training.simpleapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByDepartmentName(String name);
}