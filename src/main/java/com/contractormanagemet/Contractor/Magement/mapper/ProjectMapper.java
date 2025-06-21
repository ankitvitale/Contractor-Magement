package com.contractormanagemet.Contractor.Magement.mapper;



import com.contractormanagemet.Contractor.Magement.DTO.RequestDTO.ProjectRequestDto;
import com.contractormanagemet.Contractor.Magement.DTO.ResponseDTO.ProjectResponseDto;
import com.contractormanagemet.Contractor.Magement.Entity.Project;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring",uses ={LandMapper.class} )
public abstract class ProjectMapper {

    public abstract Project toProject(ProjectRequestDto projectRequestDto);
//    public abstract ProjectResponseDto toProjectResponseDto(Project project);
//    public abstract  ProjectResponseDto toProjectResponseDto(Project project);
public abstract  ProjectResponseDto toProjectResponseDto(Project project);


}
