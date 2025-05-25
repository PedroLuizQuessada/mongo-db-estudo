package com.quesssystems.mongodbestudo.controller.student;

import com.quesssystems.mongodbestudo.domain.student.dto.request.CreateStudentRequest;
import com.quesssystems.mongodbestudo.domain.student.dto.request.UpdateStudentRequest;
import com.quesssystems.mongodbestudo.domain.student.dto.response.StudentResponse;
import com.quesssystems.mongodbestudo.domain.student.dto.response.StudentResponseMapper;
import com.quesssystems.mongodbestudo.domain.student.model.Student;
import com.quesssystems.mongodbestudo.interfaces.Mapper;
import com.quesssystems.mongodbestudo.service.student.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/estudantes")
public class StudentControllerV1 {

    private final StudentService studentService;

    @SuppressWarnings({"rawtypes"})
    private Mapper responseMapper;

    public StudentControllerV1(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    @SuppressWarnings({"unchecked"})
    ResponseEntity<StudentResponse> create(@RequestBody @Valid CreateStudentRequest createStudentRequest) {
        log.info("Creating student with name: {}", createStudentRequest.name());
        responseMapper = new StudentResponseMapper();
        Student student = studentService.createStudent(createStudentRequest);

        return ResponseEntity
                .ok()
                .body((StudentResponse) responseMapper.map(student));
    }

    @GetMapping("/{id}")
    @SuppressWarnings({"unchecked"})
    ResponseEntity<StudentResponse> findById(@PathVariable("id") @NotNull String id) {
        log.info("Finding student by ID: {}", id);
        responseMapper = new StudentResponseMapper();
        Student student = studentService.findStudent(id);

        return ResponseEntity
                .ok()
                .body((StudentResponse) responseMapper.map(student));
    }

    @PutMapping
    @SuppressWarnings({"unchecked"})
    ResponseEntity<StudentResponse> update(@RequestBody @Valid UpdateStudentRequest updateStudentRequest) {
        log.info("Updating student ID: {}", updateStudentRequest.id());
        responseMapper = new StudentResponseMapper();
        Student student = studentService.updateStudent(updateStudentRequest);

        return ResponseEntity
                .ok()
                .body((StudentResponse) responseMapper.map(student));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") @NotNull String id) {
        log.info("Deleting student ID: {}", id);
        studentService.deleteStudent(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
