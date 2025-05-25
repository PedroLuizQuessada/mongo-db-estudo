package com.quesssystems.mongodbestudo.domain.student.persistence.mysql;

import com.quesssystems.mongodbestudo.domain.address.persistence.mysql.AddressMysql;
import com.quesssystems.mongodbestudo.domain.student.model.Student;
import com.quesssystems.mongodbestudo.interfaces.Persistence;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentMysql implements Persistence<Student> {

    @Id
    private String id;

    @Column(nullable = false, length = 45)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private AddressMysql address;

    @Override
    public Student toEntity() {
        return new Student(id, name, Objects.isNull(address) ? null : address.toEntity());
    }
}
