package com.quesssystems.mongodbestudo.controller.student;

import com.quesssystems.mongodbestudo.domain.student.dto.request.CreateStudentRequest;
import com.quesssystems.mongodbestudo.domain.student.dto.request.UpdateStudentRequest;
import com.quesssystems.mongodbestudo.domain.student.dto.response.StudentResponse;
import com.quesssystems.mongodbestudo.domain.student.dto.response.StudentResponseMapper;
import com.quesssystems.mongodbestudo.domain.student.model.Student;
import com.quesssystems.mongodbestudo.interfaces.Mapper;
import com.quesssystems.mongodbestudo.service.student.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/estudantes")
@Tag(name = "Estudante Controller V1", description = "Versão 1 do controlador referente a estudantes")
public class StudentControllerV1 {

    private final StudentService studentService;

    @SuppressWarnings({"rawtypes"})
    private Mapper responseMapper;

    public StudentControllerV1(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(summary = "Cria um estudante")
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    description = "Estudante criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentResponse.class))),
            @ApiResponse(responseCode = "400",
                    description = "Valores inválidos para os atributos do estudante a ser criado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PostMapping
    @SuppressWarnings({"unchecked"})
    ResponseEntity<StudentResponse> create(@RequestBody @Valid CreateStudentRequest createStudentRequest) {
        log.info("Creating student with name: {}", createStudentRequest.name());
        responseMapper = new StudentResponseMapper();
        Student student = studentService.createStudent(createStudentRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body((StudentResponse) responseMapper.map(student));
    }

    @Operation(summary = "Consulta um estudante por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Estudante consultado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentResponse.class))),
            @ApiResponse(responseCode = "404",
                    description = "Estudante não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
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

    @Operation(summary = "Atualiza um estudante")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Estudante atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentResponse.class))),
            @ApiResponse(responseCode = "400",
                    description = "Valores inválidos para os atributos do estudante a ser atualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404",
                    description = "Estudante a ser atualizado não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
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

    @Operation(summary = "Apaga um estudante")
    @ApiResponses({
            @ApiResponse(responseCode = "204",
                    description = "Estudante apagado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentResponse.class))),
            @ApiResponse(responseCode = "404",
                    description = "Estudante a ser atualizado não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") @NotNull String id) {
        log.info("Deleting student ID: {}", id);
        studentService.deleteStudent(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
