package com.quesssystems.mongodbestudo.domain.address.persistence.mongo;

import com.quesssystems.mongodbestudo.domain.address.model.Address;
import com.quesssystems.mongodbestudo.interfaces.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AddressMongoMapper implements Mapper<AddressMongo, Address> {
    @Override
    public AddressMongo map(Address object) {
        return new AddressMongo(object.getId(), object.getCity(), object.getStreet());
    }
}
