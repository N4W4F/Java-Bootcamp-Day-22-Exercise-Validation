package com.example.projecttracker.Controller;

import com.example.projecttracker.ApiResponse.ApiResponse;
import com.example.projecttracker.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project-tracker")
public class ProjectController {

    ArrayList<Project> projects = new ArrayList<>();

    @PostMapping("/create-project")
    public ResponseEntity createProject(@RequestBody @Valid Project project, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        projects.add(project);
        return ResponseEntity.status(200).body(new ApiResponse("Project has been created successfully"));
    }

    @GetMapping("/display-projects")
    public ResponseEntity displayProjects() {
        return ResponseEntity.status(200).body(projects);
    }

    @PutMapping("/update-project/{index}")
    public ResponseEntity updateProject(@PathVariable int index, @RequestBody @Valid Project project, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        projects.set(index, project);
        return ResponseEntity.status(200).body(new ApiResponse("Project at index " + index + " has been updated successfully"));
    }

    @DeleteMapping("/delete-project/{index}")
    public ResponseEntity deleteProject(@PathVariable int index) {
        projects.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("Project at index " + index + " has been deleted successfully"));
    }

    @PutMapping("/change-status/{index}")
    public ResponseEntity changeStatus(@PathVariable int index, @RequestBody @Valid String status, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        projects.get(index).setStatus(status);
        return ResponseEntity.status(200).body(new ApiResponse("Project status at index " + index + " has been changed successfully"));
    }

    @GetMapping("/get-project-by-title/{title}")
    public ResponseEntity getProjectByTitle(@PathVariable String title) {
        for (Project project : projects)
            if (project.getTitle().toLowerCase().contains(title))
                return ResponseEntity.status(200).body(project);

        return ResponseEntity.status(404).body(new ApiResponse("Project not found"));
    }

    @GetMapping("/get-company-projects")
    public ResponseEntity getCompanyProjects(@RequestBody @Valid String companyName, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        ArrayList<Project> companyProjects = new ArrayList<>();
        for (Project project : projects)
            if (project.getCompanyName().equalsIgnoreCase(companyName))
                companyProjects.add(project);

        return ResponseEntity.status(200).body(companyProjects);
    }
}
