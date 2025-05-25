package com.quesssystems.mongodbestudo.service.student;

import com.quesssystems.mongodbestudo.domain.student.dto.request.CreateStudentRequest;
import com.quesssystems.mongodbestudo.domain.student.dto.request.UpdateStudentRequest;
import com.quesssystems.mongodbestudo.domain.student.model.Student;

public interface StudentService {
    Student createStudent(CreateStudentRequest createStudentRequest);
    Student updateStudent(UpdateStudentRequest updateStudentRequest);
    void deleteStudent(String id);
    Student findStudent(String id);
}
