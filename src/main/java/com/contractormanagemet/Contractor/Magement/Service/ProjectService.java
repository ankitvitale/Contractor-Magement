package com.contractormanagemet.Contractor.Magement.Service;

import com.contractormanagemet.Contractor.Magement.DTO.RequestDTO.ProjectRequestDto;
import com.contractormanagemet.Contractor.Magement.DTO.ResponseDTO.ProjectResponseDto;
import com.contractormanagemet.Contractor.Magement.Entity.Land;
import com.contractormanagemet.Contractor.Magement.Entity.Project;
import com.contractormanagemet.Contractor.Magement.Entity.Superisor;
import com.contractormanagemet.Contractor.Magement.Exception.ResourceNotFoundException;
import com.contractormanagemet.Contractor.Magement.Repository.LandRepository;
import com.contractormanagemet.Contractor.Magement.Repository.ProjectRepository;
import com.contractormanagemet.Contractor.Magement.Repository.SuperisorRepository;
import com.contractormanagemet.Contractor.Magement.mapper.ProjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private SuperisorRepository superisorRepository;

    @Autowired
    private LandRepository landRepository;
    public Project createProject(ProjectRequestDto projectRequestDto) {
        Project project=projectMapper.toProject(projectRequestDto);
        Land land=landRepository.findById(projectRequestDto.getLandId())
              .orElseThrow(()-> new EntityNotFoundException("lanf Id is not Found"));
        project.setLand(land);
        return projectRepository.save(project);
    }

    public List<?> getAllProjects() {
        List<Project> projects=projectRepository.findAll();
        return projects.stream()
                .map(projectMapper::toProjectResponseDto)
                .collect(Collectors.toList());
    }

    public ProjectResponseDto getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElse(null); // You can throw an exception here if you prefer
        if (project == null) {
            return null; // Or you could throw a custom exception if the project is not found
        }
        // Map entity to DTO
        return projectMapper.toProjectResponseDto(project);
    }

    public Project updateProject(Long id, ProjectResponseDto projectResponseDto) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + id));

        // Map the fields from the DTO to the Project entity
        project.setName(projectResponseDto.getName());
        project.setStatus(projectResponseDto.getStatus());
        project.setBuildingSize(projectResponseDto.getBuildingSize());
        project.setTotalflat(projectResponseDto.getTotalflat());
        project.setArea(projectResponseDto.getArea());
        project.setFacing(projectResponseDto.getFacing());
        // Save the updated project back to the repository
        Project updatedProject = projectRepository.save(project);

        return updatedProject;
    }


    public void deleteProject(Long id) {
        // Check if the project exists
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + id));
        // Delete related entries from residency
        projectRepository.deleteResidenciesByProjectId(id);
        // Delete related entries from expense_installment

     //   projectRepository.deleteExpenseInstallmentsByProjectId(id);
  //      projectRepository.deleteExpensesByProjectId(id);
        // Delete related entries in the app_user_allowed_site table
     //   projectRepository.deleteAppUserAllowedSiteByProjectId(id);

        // Delete the project itself
        projectRepository.deleteProjectById(id);
    }

    public Project allowedSiteSupervisor(Long userId, Long projectId) {
        Superisor supervisor = superisorRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Supervisor with ID " + userId + " not found"));

//        if (!supervisor.getUserType().equals(UserType.AppUser)) {
//            throw new IllegalArgumentException("User with ID " + userId + " is not a valid site supervisor");
//        }

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project with ID " + projectId + " not found"));

        // Add supervisor to the project
        project.getSupervisors().add(supervisor);
        supervisor.getAllowedSite().add(project);

        superisorRepository.save(supervisor);
        return projectRepository.save(project);
    }

    public Project releaseSiteSupervisor(Long userId, Long projectId) {
        // Fetch the supervisor (AppUser) details
        Superisor supervisor = superisorRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Supervisor with ID " + userId + " not found"));

        // Fetch the project
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project with ID " + projectId + " not found"));

//        // Check if the supervisor is currently assigned to the project
//        if (!project.getSupervisors().equals(supervisor)) {
//            throw new IllegalArgumentException("Supervisor with ID " + userId + " is not assigned to this project");
//        }

        // Remove the supervisor from the project
        project.setSupervisors(null); // Assuming `setAppUser` links the supervisor to the project, we set it to null

        // Optionally, remove the project from the supervisor's list of allowed sites
        supervisor.getAllowedSite().remove(project);
        superisorRepository.save(supervisor);

        // Save and return the updated project
        return projectRepository.save(project);
    }
}
