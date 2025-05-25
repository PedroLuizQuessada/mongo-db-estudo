package com.quesssystems.mongodbestudo.domain.student.persistence.mongo;

import com.quesssystems.mongodbestudo.domain.address.persistence.mongo.AddressMongo;
import com.quesssystems.mongodbestudo.domain.student.model.Student;
import com.quesssystems.mongodbestudo.interfaces.Persistence;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "students")
@AllArgsConstructor
public class StudentMongo implements Persistence<Student> {

    @Id
    private String id;

    private String name;

    @DBRef
    private AddressMongo address;

    @Override
    public Student toEntity() {
        return new Student(this.id, this.name, Objects.isNull(address) ? null : this.address.toEntity());
    }
}
