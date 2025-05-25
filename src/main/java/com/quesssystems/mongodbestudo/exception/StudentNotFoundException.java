package com.quesssystems.mongodbestudo.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String id) {
        super(String.format("Estudante de ID %s não encontado", id));
    }
}
