package com.quesssystems.mongodbestudo.repository.student;

import com.quesssystems.mongodbestudo.domain.student.model.Student;
import com.quesssystems.mongodbestudo.domain.student.persistence.mongo.StudentMongo;
import com.quesssystems.mongodbestudo.domain.student.persistence.mongo.StudentMongoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StudentMongoRepositoryImpl implements StudentRepository {

    private final StudentMongoMapper studentMongoMapper;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    public StudentMongoRepositoryImpl(StudentMongoMapper studentMongoMapper) {
        this.studentMongoMapper = studentMongoMapper;
    }

    @Override
    public Student save(Student student) {
        StudentMongo studentMongo = studentMongoMapper.map(student);
        return mongoTemplate.save(studentMongo).toEntity();
    }

    @Override
    public Optional<Student> findById(String id) {
        Optional<StudentMongo> optionalStudentMongo = Optional.ofNullable(mongoTemplate.findById(id, StudentMongo.class));
        return optionalStudentMongo.map(StudentMongo::toEntity);
    }

    @Override
    public void delete(Student student) {
        StudentMongo studentMongo = studentMongoMapper.map(student);
        mongoTemplate.remove(studentMongo);
    }
}
