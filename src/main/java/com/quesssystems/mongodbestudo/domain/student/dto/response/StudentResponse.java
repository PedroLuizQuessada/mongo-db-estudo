package com.quesssystems.mongodbestudo.domain.student.dto.response;

import com.quesssystems.mongodbestudo.domain.address.dto.response.AddressResponse;

public record StudentResponse(String name, AddressResponse address) {
}
