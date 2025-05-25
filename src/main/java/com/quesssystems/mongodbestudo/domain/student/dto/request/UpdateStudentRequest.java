package com.quesssystems.mongodbestudo.domain.student.dto.request;

import com.quesssystems.mongodbestudo.domain.address.dto.request.UpdateAddressRequest;

public record UpdateStudentRequest(String id, String name, UpdateAddressRequest address) {
}
