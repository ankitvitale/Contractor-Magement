package com.contractormanagemet.Contractor.Magement.Controller;

import com.contractormanagemet.Contractor.Magement.DTO.SuperisorDto.RequestSuperisor;
import com.contractormanagemet.Contractor.Magement.Entity.*;
import com.contractormanagemet.Contractor.Magement.Service.JwtService;
import com.contractormanagemet.Contractor.Magement.Service.ProjectService;
import com.contractormanagemet.Contractor.Magement.Service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private ProjectService projectService;
    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }
    @PostMapping("/registerAdmin")
    public Admin registerNewAdmin(@RequestBody Admin admin) {
        return userService.registerAdmin(admin);
    }



    @PostMapping("/registerSuperisor")
    public ResponseEntity<RequestSuperisor> registerSuperisor(@RequestBody RequestSuperisor dto) {
        Superisor registered = userService.registerSuperisor(dto);

        RequestSuperisor response = new RequestSuperisor();
        response.setName(registered.getName());
        response.setEmail(registered.getEmail());
        response.setPassword(registered.getPassword());

        return ResponseEntity.ok(response); // no password in response
    }

    @PostMapping("/auth/login")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }

    @PutMapping("/allowedSiteSupervisor/{userId}/{projectId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Project> allowedSiteSupervisor(@PathVariable("userId") Long userId, @PathVariable("projectId") Long projectId) {
        Project allowedProject = projectService.allowedSiteSupervisor(userId, projectId);
        return ResponseEntity.ok(allowedProject);
    }

//    @GetMapping("/all-supervisors")
//    @PreAuthorize("hasRole('Admin')")
//    public List<RequestSuperisor> getAllSupervisors() {
//        return userService.getAllSupervisors();
//    }

    @PutMapping("/releaseSiteSupervisor/{userId}/{projectId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Project> releaseSiteSupervisor(@PathVariable("userId") Long userId, @PathVariable("projectId") Long projectId) {
        Project updatedProject = projectService.releaseSiteSupervisor(userId, projectId);
        return ResponseEntity.ok(updatedProject);
    }
}
