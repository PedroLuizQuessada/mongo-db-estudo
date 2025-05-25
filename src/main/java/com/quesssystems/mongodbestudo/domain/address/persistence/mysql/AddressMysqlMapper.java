package com.quesssystems.mongodbestudo.domain.address.persistence.mysql;

import com.quesssystems.mongodbestudo.domain.address.model.Address;
import com.quesssystems.mongodbestudo.interfaces.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AddressMysqlMapper implements Mapper<AddressMysql, Address> {
    @Override
    public AddressMysql map(Address object) {
        return new AddressMysql(object.getId(), object.getCity(), object.getStreet());
    }
}
