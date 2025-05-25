package com.quesssystems.mongodbestudo.domain.student.persistence.mongo;

import com.quesssystems.mongodbestudo.domain.address.persistence.mongo.AddressMongoMapper;
import com.quesssystems.mongodbestudo.domain.student.model.Student;
import com.quesssystems.mongodbestudo.interfaces.Mapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class StudentMongoMapper implements Mapper<StudentMongo, Student> {

    private final AddressMongoMapper addressMongoMapper;

    public StudentMongoMapper() {
        this.addressMongoMapper = new AddressMongoMapper();
    }

    @Override
    public StudentMongo map(Student object) {
        return new StudentMongo(object.getId(), object.getName(), Objects.isNull(object.getAddress()) ? null : addressMongoMapper.map(object.getAddress()));
    }
}
