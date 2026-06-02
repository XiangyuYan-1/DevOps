package fr.takima.training.simpleapi.controller;

import fr.takima.training.simpleapi.entity.Department;
import fr.takima.training.simpleapi.entity.Student;
import fr.takima.training.simpleapi.repository.DepartmentRepository;
import fr.takima.training.simpleapi.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;

    public DepartmentController(StudentRepository studentRepository, DepartmentRepository departmentRepository) {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/departments")
    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/departments/{name}/students")
    public List<Student> getStudentsByDepartment(@PathVariable String name) {
        return studentRepository.findByDepartmentName(name);
    }
}