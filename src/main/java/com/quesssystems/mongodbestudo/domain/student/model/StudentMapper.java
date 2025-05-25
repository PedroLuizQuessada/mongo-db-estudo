package com.quesssystems.mongodbestudo.domain.student.model;

import com.quesssystems.mongodbestudo.domain.address.model.AddressMapper;
import com.quesssystems.mongodbestudo.domain.student.dto.request.CreateStudentRequest;
import com.quesssystems.mongodbestudo.domain.student.dto.request.UpdateStudentRequest;
import com.quesssystems.mongodbestudo.interfaces.EntityMapper;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.Set;

public class StudentMapper implements EntityMapper<Student> {

    private final Validator validator;

    private final AddressMapper addressMapper;

    @Autowired
    public StudentMapper(Validator validator, AddressMapper addressMapper) {
        this.validator = validator;
        this.addressMapper = addressMapper;
    }

    public Student toEntity(CreateStudentRequest createStudentRequest) {
        Student student = new Student(null, createStudentRequest.name(),
                Objects.isNull(createStudentRequest.address()) ? null : addressMapper.toEntity(createStudentRequest.address()));

        validateEntity(student);

        return student;
    }

    public Student toEntity(UpdateStudentRequest updateStudentRequest) {
        Student student = new Student(updateStudentRequest.id(), updateStudentRequest.name(),
                Objects.isNull(updateStudentRequest.address()) ? null : addressMapper.toEntity(updateStudentRequest.address()));

        validateEntity(student);

        return student;
    }

    @Override
    public void validateEntity(Student student) {
        Set<ConstraintViolation<Student>> violations = validator.validate(student);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
