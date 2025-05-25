package com.quesssystems.mongodbestudo.repository.student;

import com.quesssystems.mongodbestudo.domain.student.model.Student;

import java.util.Optional;

public interface StudentRepository {
    Student save(Student student);
    Optional<Student> findById(String id);
    void delete(Student student);
}
