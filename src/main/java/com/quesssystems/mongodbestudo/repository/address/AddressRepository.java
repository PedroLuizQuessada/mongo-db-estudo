package com.quesssystems.mongodbestudo.repository.address;

import com.quesssystems.mongodbestudo.domain.address.model.Address;

public interface AddressRepository {
    void save(Address address);
    void delete(Address address);
}
