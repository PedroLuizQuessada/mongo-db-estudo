package com.quesssystems.mongodbestudo.interfaces;

public interface EntityMapper<T extends Model> {
    void validateEntity(T entity);
}
