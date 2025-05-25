package com.quesssystems.mongodbestudo.domain.address.model;

import com.quesssystems.mongodbestudo.domain.address.dto.request.CreateAddressRequest;
import com.quesssystems.mongodbestudo.domain.address.dto.request.UpdateAddressRequest;
import com.quesssystems.mongodbestudo.interfaces.EntityMapper;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class AddressMapper implements EntityMapper<Address> {

    private final Validator validator;

    @Autowired
    public AddressMapper(Validator validator) {
        this.validator = validator;
    }

    public Address toEntity(CreateAddressRequest createAddressRequest) {
        Address address = new Address(null, createAddressRequest.city(), createAddressRequest.street());

        validateEntity(address);

        return address;
    }

    public Address toEntity(UpdateAddressRequest updateAddressRequest) {
        Address address = new Address(updateAddressRequest.id(), updateAddressRequest.city(), updateAddressRequest.street());

        validateEntity(address);

        return address;
    }

    @Override
    public void validateEntity(Address address) {
        Set<ConstraintViolation<Address>> violations = validator.validate(address);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
