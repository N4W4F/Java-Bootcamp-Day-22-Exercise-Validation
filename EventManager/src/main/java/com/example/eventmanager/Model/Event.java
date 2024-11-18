package com.example.eventmanager.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Event {
    @NotEmpty(message = "ID cannot be empty")
    @Size(min = 3, message = "ID must be more than 2 digits")
    private String id;

    @NotEmpty(message = "Description cannot be empty")
    @Size(min = 16, message = "Description must be more than 15 characters")
    private String description;

    @NotNull(message = "Capacity cannot be empty")
    @Digits(integer = 10, fraction = 0, message = "Capacity must be valid integer")
    @Min(value = 26, message = "Capacity must be more than 25")
    private int capacity;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endDate;
}
