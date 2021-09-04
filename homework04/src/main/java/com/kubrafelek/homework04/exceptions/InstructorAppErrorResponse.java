package com.kubrafelek.homework04.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorAppErrorResponse {
    private int status;
    private String message;
    private long timestamp;
}
