package com.quesssystems.mongodbestudo.interfaces;

public interface Mapper<T, U> {
    T map(U object);
}
