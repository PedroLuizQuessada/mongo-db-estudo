package com.quesssystems.mongodbestudo.interfaces;

public interface Persistence<T extends Model> {
    T toEntity();
}
