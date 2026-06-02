package fr.takima.training.simpleapi.repository;

import fr.takima.training.simpleapi.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}