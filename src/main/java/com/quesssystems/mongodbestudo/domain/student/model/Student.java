package com.quesssystems.mongodbestudo.domain.student.model;

import com.quesssystems.mongodbestudo.domain.address.model.Address;
import com.quesssystems.mongodbestudo.interfaces.Model;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

public class Student extends Model {

    @Getter
    @NotNull(message = "O estudante deve possuir um ID")
    private final String id;

    @Getter
    @NotNull(message = "O estudante deve possuir um nome")
    private final String name;

    @Getter
    private final Address address;

    public Student(String id, String name, Address address) {
        this.id = Objects.isNull(id) ? String.valueOf(UUID.randomUUID()) : id;
        this.name = name;
        this.address = address;
    }
}
