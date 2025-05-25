package com.quesssystems.mongodbestudo.enums;

public enum ApplicationPersistenceTecnologyEnum {
    MYSQL("mysql"),
    MONGO("mongo");

    private final String value;

    ApplicationPersistenceTecnologyEnum(String value) {
        this.value = value;
    }

    public static ApplicationPersistenceTecnologyEnum getByValue(String value) {
        for (ApplicationPersistenceTecnologyEnum applicationPersistenceTecnology : ApplicationPersistenceTecnologyEnum.values()) {
            if (applicationPersistenceTecnology.value.equals(value))
                return applicationPersistenceTecnology;
        }

        return null;
    }
}
