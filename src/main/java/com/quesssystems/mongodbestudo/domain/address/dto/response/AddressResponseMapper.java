package com.quesssystems.mongodbestudo.domain.address.dto.response;

import com.quesssystems.mongodbestudo.domain.address.model.Address;
import com.quesssystems.mongodbestudo.interfaces.Mapper;

public class AddressResponseMapper implements Mapper<AddressResponse, Address> {

    @Override
    public AddressResponse map(Address object) {
        return new AddressResponse(object.getCity(), object.getStreet());
    }
}
