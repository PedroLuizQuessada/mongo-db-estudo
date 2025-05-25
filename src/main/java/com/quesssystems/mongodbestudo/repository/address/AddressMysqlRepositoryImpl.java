package com.quesssystems.mongodbestudo.repository.address;

import com.quesssystems.mongodbestudo.domain.address.model.Address;
import com.quesssystems.mongodbestudo.domain.address.persistence.mysql.AddressMysql;
import com.quesssystems.mongodbestudo.domain.address.persistence.mysql.AddressMysqlMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AddressMysqlRepositoryImpl implements AddressRepository {

    private final AddressMysqlMapper addressMysqlMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public AddressMysqlRepositoryImpl(AddressMysqlMapper addressMysqlMapper) {
        this.addressMysqlMapper = addressMysqlMapper;
    }

    @Override
    @Transactional
    public void save(Address address) {
        AddressMysql addressMysql = addressMysqlMapper.map(address);
        entityManager.merge(addressMysql).toEntity();
    }

    @Override
    @Transactional
    public void delete(Address address) {
        AddressMysql addressMysql = addressMysqlMapper.map(address);
        addressMysql = entityManager.merge(addressMysql);
        entityManager.remove(addressMysql);
    }
}
