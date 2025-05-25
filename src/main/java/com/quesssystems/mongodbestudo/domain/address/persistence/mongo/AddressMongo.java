package com.quesssystems.mongodbestudo.domain.address.persistence.mongo;

import com.quesssystems.mongodbestudo.domain.address.model.Address;
import com.quesssystems.mongodbestudo.interfaces.Persistence;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "addresses")
@AllArgsConstructor
public class AddressMongo implements Persistence<Address> {

    @Id
    private String id;

    private String city;

    private String street;

    @Override
    public Address toEntity() {
        return new Address(id, city, street);
    }
}
