package com.quesssystems.mongodbestudo.repository.address;

import com.quesssystems.mongodbestudo.domain.address.model.Address;
import com.quesssystems.mongodbestudo.domain.address.persistence.mongo.AddressMongo;
import com.quesssystems.mongodbestudo.domain.address.persistence.mongo.AddressMongoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AddressMongoRepositoryImpl implements AddressRepository {

    private final AddressMongoMapper addressMongoMapper;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    public AddressMongoRepositoryImpl(AddressMongoMapper addressMongoMapper) {
        this.addressMongoMapper = addressMongoMapper;
    }

    @Override
    public void save(Address address) {
        AddressMongo addressMongo = addressMongoMapper.map(address);
        mongoTemplate.save(addressMongo).toEntity();
    }

    @Override
    public void delete(Address address) {
        AddressMongo addressMongo = addressMongoMapper.map(address);
        mongoTemplate.remove(addressMongo);
    }
}
