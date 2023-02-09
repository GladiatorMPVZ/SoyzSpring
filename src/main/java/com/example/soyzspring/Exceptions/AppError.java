package com.example.soyzspring.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class AppError {
    private Integer code;
    private String message;
}
