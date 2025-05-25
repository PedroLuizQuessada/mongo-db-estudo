package com.quesssystems.mongodbestudo.domain.address.model;

import com.quesssystems.mongodbestudo.interfaces.Model;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

public class Address extends Model {

    @Getter
    private final String id;

    @Getter
    @NotBlank(message = "O endereço deve possuir uma cidade")
    private final String city;

    @Getter
    @NotBlank(message = "O endereço deve possuir uma rua")
    private final String street;

    public Address(String id, String city, String street) {
        this.id = Objects.isNull(id) ? String.valueOf(UUID.randomUUID()) : id;
        this.city = city;
        this.street = street;
    }
}
