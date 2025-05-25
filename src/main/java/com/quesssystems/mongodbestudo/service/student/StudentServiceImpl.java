package com.quesssystems.mongodbestudo.service.student;

import com.quesssystems.mongodbestudo.domain.student.dto.request.CreateStudentRequest;
import com.quesssystems.mongodbestudo.domain.student.dto.request.UpdateStudentRequest;
import com.quesssystems.mongodbestudo.domain.student.model.Student;
import com.quesssystems.mongodbestudo.domain.student.model.StudentMapper;
import com.quesssystems.mongodbestudo.exception.StudentNotFoundException;
import com.quesssystems.mongodbestudo.repository.address.AddressRepository;
import com.quesssystems.mongodbestudo.repository.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final AddressRepository addressRepository;

    private final StudentMapper studentMapper;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository,
                              AddressRepository addressRepository,
                              StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.addressRepository = addressRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public Student createStudent(CreateStudentRequest createStudentRequest) {
        Student student = studentMapper.toEntity(createStudentRequest);
        if (!Objects.isNull(student.getAddress()))
            addressRepository.save(student.getAddress());
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(UpdateStudentRequest updateStudentRequest) {
        Student student = studentMapper.toEntity(updateStudentRequest);
        Student oldStudent = findStudent(student.getId());
        student = studentRepository.save(student);
        if (Objects.isNull(student.getAddress()) && !Objects.isNull(oldStudent.getAddress())) {
            addressRepository.delete(oldStudent.getAddress());
        }
        else if (!Objects.isNull(student.getAddress())) {
            addressRepository.save(student.getAddress());
        }
        return student;
    }

    @Override
    public void deleteStudent(String id) {
        Student student = this.findStudent(id);
        if (!Objects.isNull(student.getAddress()))
            addressRepository.delete(student.getAddress());
        studentRepository.delete(student);
    }

    @Override
    public Student findStudent(String id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty()) {
            throw new StudentNotFoundException(id);
        }
        return optionalStudent.get();
    }
}
