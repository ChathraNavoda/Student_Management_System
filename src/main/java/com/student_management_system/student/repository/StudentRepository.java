package com.student_management_system.student.repository;

import com.student_management_system.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findAllByEmail(String email);
}
