package com.student_management.backend.repository;

import com.student_management.backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long> {


}
