package com.quesssystems.mongodbestudo.domain.student.dto.request;

import com.quesssystems.mongodbestudo.domain.address.dto.request.CreateAddressRequest;

public record CreateStudentRequest(String name, CreateAddressRequest address) {
}
