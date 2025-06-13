package com.contractormanagemet.Contractor.Magement.Service;


import com.contractormanagemet.Contractor.Magement.DTO.ResidencyDto.ResidencyRequestDto;
import com.contractormanagemet.Contractor.Magement.DTO.ResidencyDto.ResidencyResponseDto;
import com.contractormanagemet.Contractor.Magement.Entity.Project;
import com.contractormanagemet.Contractor.Magement.Entity.Residency;
import com.contractormanagemet.Contractor.Magement.Entity.enumeration.AvailabilityStatus;
import com.contractormanagemet.Contractor.Magement.Repository.ProjectRepository;
import com.contractormanagemet.Contractor.Magement.Repository.ResidencyRepository;
import com.contractormanagemet.Contractor.Magement.mapper.ResidencyMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResidencyService {
    @Autowired
    private ResidencyRepository residencyRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ResidencyMapper residencyMapper;

    @Transactional
    public Residency createResidency(ResidencyRequestDto dto) {
        // Map DTO to Entity excluding the project
        Residency residency = residencyMapper.toEntity(dto);

        // Fetch and set the project entity
        Project project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + dto.getProjectId()));

        residency.setProject(project);

        // Save and return
        return residencyRepository.save(residency);
    }


    public List<Residency> getAllResidencies() {
        return residencyRepository.findAll();
    }
    public Residency getResidencyById(Long id) {
        return residencyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Residency not found with ID: " + id));
    }


    public Residency updateResidency(Long id, ResidencyRequestDto residencyDto) {
        Residency existingResidency = residencyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Residency not found with ID: " + id));

        // Update residency fields
        existingResidency.setName(residencyDto.getName());
        existingResidency.setResidencyType(residencyDto.getResidencyType());
        existingResidency.setFlatType(residencyDto.getFlatType());
        existingResidency.setAvailabilityStatus(residencyDto.getAvailabilityStatus());
        existingResidency.setFloorNumber(residencyDto.getFloorNumber());
        existingResidency.setIdentifier(residencyDto.getIdentifier());
        existingResidency.setPrice(residencyDto.getPrice());

        // Update project association if projectId is provided
        if (residencyDto.getProjectId() != null) {
            Project project = projectRepository.findById(residencyDto.getProjectId())
                    .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + residencyDto.getProjectId()));
            existingResidency.setProject(project);
        }

        // Save and return the updated residency
        return residencyRepository.save(existingResidency);
    }

    public Residency deleteResidency(Long id) {
        Residency residency=residencyRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Residency ID Not Found"));
        residencyRepository.deleteById(id);
        return residency;
    }
    public List<Residency> getResidenciesByProject(Long projectId) {
        return residencyRepository.findByProjectIdAndAvailabilityStatus(projectId, AvailabilityStatus.BOOKED);

    }


    public List<ResidencyResponseDto> getResidenciesByProjectId(Long projectId) {

            List<Residency> residencies = residencyRepository.findAllByProjectId(projectId);


            // Map Residency entities to DTOs
            return residencies.stream().map(residency -> {
                ResidencyResponseDto dto = new ResidencyResponseDto();
                dto.setId(residency.getId());
                dto.setName(residency.getName());
                dto.setResidencyType(residency.getResidencyType());
                dto.setFlatType(residency.getFlatType());
                dto.setAvailabilityStatus(residency.getAvailabilityStatus());
                dto.setFloorNumber(residency.getFloorNumber());
                dto.setIdentifier(residency.getIdentifier());
                dto.setPrice(residency.getPrice());
                dto.setProjectId(residency.getProject().getId());
                return dto;
            }).toList();
    }

    public Map<Long, Map<String, Long>> countResidenciesByProjectAndStatus() {
        List<Object[]> results = residencyRepository.countByProjectIdAndAvailabilityStatus();

        // Map to store the results by projectId and availability status
        Map<Long, Map<String, Long>> projectStatusCountMap = new HashMap<>();

        // Iterate over the query results and populate the map
        for (Object[] result : results) {
            Long projectId = (Long) result[0];
            AvailabilityStatus availabilityStatus = (AvailabilityStatus) result[1];
            Long count = (Long) result[2];

            // Get or create the map for the given projectId
            projectStatusCountMap
                    .computeIfAbsent(projectId, k -> new HashMap<>())
                    .put(availabilityStatus.name(), count);
        }

        return projectStatusCountMap;
    }
}
