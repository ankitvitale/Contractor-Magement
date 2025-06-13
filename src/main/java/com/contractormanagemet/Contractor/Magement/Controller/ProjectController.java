package com.contractormanagemet.Contractor.Magement.Controller;


import com.contractormanagemet.Contractor.Magement.DTO.RequestDTO.ProjectRequestDto;
import com.contractormanagemet.Contractor.Magement.DTO.ResponseDTO.ProjectResponseDto;
import com.contractormanagemet.Contractor.Magement.Entity.Project;
import com.contractormanagemet.Contractor.Magement.Service.ProjectService;
import com.contractormanagemet.Contractor.Magement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;


    @PostMapping("/createProject")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Project> createProject(@RequestBody ProjectRequestDto projectRequestDto){
      Project createProject= projectService.createProject(projectRequestDto);
      return ResponseEntity.ok(createProject);
    }
    @GetMapping("/getAllProjects")
    @PreAuthorize("hasAnyRole('Admin','AppUser')")
    public ResponseEntity<List<?>> getAllProject(Authentication authentication){
        String email = authentication.getName();
        List<?> projects;

        // Check if the logged-in user is an Admin
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_Admin"));

        if (isAdmin) {
            // Admin gets all projects
            projects = projectService.getAllProjects();
        } else {
            // AppUser gets only allowed sites
            projects = userService.getAllowedSitesForUser(email);
        }

        if (!projects.isEmpty()) {
            return ResponseEntity.ok(projects);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/getProjectById/{id}")
    public ResponseEntity<ProjectResponseDto> getSingleProject(@PathVariable Long  id){
        ProjectResponseDto project = projectService.getProjectById(id);
        if (project != null) {
            return ResponseEntity.ok(project);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/updateProject/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody ProjectResponseDto projectResponseDto) {

        Project updatedProject = projectService.updateProject(id, projectResponseDto);
        return ResponseEntity.ok(updatedProject);  // Return updated project as response
    }

    @DeleteMapping("/deleteProject/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

}
