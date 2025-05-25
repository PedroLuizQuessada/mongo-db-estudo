package com.quesssystems.mongodbestudo.domain.student.persistence.mysql;

import com.quesssystems.mongodbestudo.domain.address.persistence.mysql.AddressMysqlMapper;
import com.quesssystems.mongodbestudo.domain.student.model.Student;
import com.quesssystems.mongodbestudo.interfaces.Mapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class StudentMysqlMapper implements Mapper<StudentMysql, Student> {

    private final AddressMysqlMapper addressMysqlMapper;

    public StudentMysqlMapper() {
        this.addressMysqlMapper = new AddressMysqlMapper();
    }

    @Override
    public StudentMysql map(Student object) {
        return new StudentMysql(object.getId(), object.getName(), Objects.isNull(object.getAddress()) ? null : addressMysqlMapper.map(object.getAddress()));
    }
}
