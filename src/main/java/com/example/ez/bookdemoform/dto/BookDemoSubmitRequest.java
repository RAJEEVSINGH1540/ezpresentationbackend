// src/main/java/com/example/ez/dto/BookDemoSubmitRequest.java
package com.example.ez.bookdemoform.dto;

import lombok.*;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDemoSubmitRequest {
    private Map<String, String> formData;
}