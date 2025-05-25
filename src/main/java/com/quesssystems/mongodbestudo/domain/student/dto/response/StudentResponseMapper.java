package com.quesssystems.mongodbestudo.domain.student.dto.response;

import com.quesssystems.mongodbestudo.domain.address.dto.response.AddressResponseMapper;
import com.quesssystems.mongodbestudo.domain.student.model.Student;
import com.quesssystems.mongodbestudo.interfaces.Mapper;

import java.util.Objects;

public class StudentResponseMapper implements Mapper<StudentResponse, Student> {

    private final AddressResponseMapper addressResponseMapper;

    public StudentResponseMapper() {
        this.addressResponseMapper = new AddressResponseMapper();
    }

    @Override
    public StudentResponse map(Student object) {
        return new StudentResponse(object.getName(), Objects.isNull(object.getAddress()) ? null : addressResponseMapper.map(object.getAddress()));
    }
}
