package com.quesssystems.mongodbestudo.repository.student;

import com.quesssystems.mongodbestudo.domain.student.model.Student;
import com.quesssystems.mongodbestudo.domain.student.persistence.mysql.StudentMysql;
import com.quesssystems.mongodbestudo.domain.student.persistence.mysql.StudentMysqlMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class StudentMysqlRepositoryImpl implements StudentRepository {

    private final StudentMysqlMapper studentMysqlMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public StudentMysqlRepositoryImpl(StudentMysqlMapper studentMysqlMapper) {
        this.studentMysqlMapper = studentMysqlMapper;
    }

    @Override
    @Transactional
    public Student save(Student student) {
        StudentMysql studentMysql = studentMysqlMapper.map(student);
        return entityManager.merge(studentMysql).toEntity();
    }

    @Override
    public Optional<Student> findById(String id) {
        Optional<StudentMysql> optionalStudentMysql = Optional.ofNullable(entityManager.find(StudentMysql.class, id));
        return optionalStudentMysql.map(StudentMysql::toEntity);
    }

    @Override
    @Transactional
    public void delete(Student student) {
        StudentMysql studentMysql = studentMysqlMapper.map(student);
        studentMysql = entityManager.merge(studentMysql);
        entityManager.remove(studentMysql);
    }
}
