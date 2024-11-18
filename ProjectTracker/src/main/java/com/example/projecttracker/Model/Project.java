package com.example.projecttracker.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    @NotEmpty(message = "ID cannot be empty")
    @Size(min = 3, message = "ID must be more than 2 digits")
    private String id;

    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 9, message = "Title length must be more than 8 characters")
    private String title;

    @NotEmpty(message = "Description cannot be empty")
    @Size(min = 16, message = "Description must be more than 15 characters")
    private String description;

    @NotEmpty(message = "Status cannot be empty")
    @Pattern(regexp = "^(Not Started|in Progress|Completed)$",
            message = "Status must be 'Not Started', 'in Progress', or 'Completed'.")
    private String status;

    @NotEmpty(message = "Company Name cannot be empty")
    @Size(min = 7, message = "Company Name must be more than 6 characters")
    private String companyName;
}
