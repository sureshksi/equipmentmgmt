package com.education.lending.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum RequestStatus {
	PENDING, APPROVED, REJECTED, RETURNED;
	
    @JsonCreator
    public static RequestStatus fromString(String value) {
        return value == null ? null : RequestStatus.valueOf(value.toUpperCase());
    }
}
