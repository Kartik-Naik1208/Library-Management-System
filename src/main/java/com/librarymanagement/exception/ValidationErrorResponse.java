package com.librarymanagement.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Validation Error Response DTO for sending validation errors to client
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidationErrorResponse {

    private int status;
    private String message;
    private Map<String, String> errors;
    private String path;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;
}
