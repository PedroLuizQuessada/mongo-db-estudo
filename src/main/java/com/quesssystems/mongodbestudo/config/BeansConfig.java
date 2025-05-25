package com.quesssystems.mongodbestudo.config;

import com.quesssystems.mongodbestudo.domain.address.model.AddressMapper;
import com.quesssystems.mongodbestudo.domain.address.persistence.mongo.AddressMongoMapper;
import com.quesssystems.mongodbestudo.domain.address.persistence.mysql.AddressMysqlMapper;
import com.quesssystems.mongodbestudo.domain.student.model.StudentMapper;
import com.quesssystems.mongodbestudo.domain.student.persistence.mongo.StudentMongoMapper;
import com.quesssystems.mongodbestudo.domain.student.persistence.mysql.StudentMysqlMapper;
import com.quesssystems.mongodbestudo.enums.ApplicationPersistenceTecnologyEnum;
import com.quesssystems.mongodbestudo.repository.address.AddressMongoRepositoryImpl;
import com.quesssystems.mongodbestudo.repository.address.AddressMysqlRepositoryImpl;
import com.quesssystems.mongodbestudo.repository.address.AddressRepository;
import com.quesssystems.mongodbestudo.repository.student.StudentMongoRepositoryImpl;
import com.quesssystems.mongodbestudo.repository.student.StudentMysqlRepositoryImpl;
import com.quesssystems.mongodbestudo.repository.student.StudentRepository;
import com.quesssystems.mongodbestudo.service.student.StudentService;
import com.quesssystems.mongodbestudo.service.student.StudentServiceImpl;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Value("${application.persistence.tecnology}")
    private String applicationPersistenceTecnology;

    @Bean
    public StudentService studentService() {
        return new StudentServiceImpl(studentRepository(), addressRepository(), studentMapper());
    }

    @Bean
    public StudentRepository studentRepository() {
        switch (ApplicationPersistenceTecnologyEnum.getByValue(applicationPersistenceTecnology)) {
            case MONGO -> {
                return new StudentMongoRepositoryImpl(new StudentMongoMapper());
            }
            case null, default -> {
                return new StudentMysqlRepositoryImpl(new StudentMysqlMapper());
            }
        }
    }

    @Bean
    public AddressRepository addressRepository() {
        switch (ApplicationPersistenceTecnologyEnum.getByValue(applicationPersistenceTecnology)) {
            case MONGO -> {
                return new AddressMongoRepositoryImpl(new AddressMongoMapper());
            }
            case null, default -> {
                return new AddressMysqlRepositoryImpl(new AddressMysqlMapper());
            }
        }
    }

    @Bean
    public StudentMapper studentMapper() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return new StudentMapper(factory.getValidator(), addressMapper());
    }

    @Bean
    public AddressMapper addressMapper() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return new AddressMapper(factory.getValidator());
    }
}
