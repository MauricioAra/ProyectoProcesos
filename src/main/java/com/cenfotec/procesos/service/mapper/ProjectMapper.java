package com.cenfotec.procesos.service.mapper;

import com.cenfotec.procesos.domain.*;
import com.cenfotec.procesos.service.dto.ProjectDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Project and its DTO ProjectDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProjectMapper {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "company.name", target = "companyName")
    ProjectDTO projectToProjectDTO(Project project);

    List<ProjectDTO> projectsToProjectDTOs(List<Project> projects);

    @Mapping(source = "companyId", target = "company")
    @Mapping(target = "tasks", ignore = true)
    @Mapping(target = "risks", ignore = true)
    Project projectDTOToProject(ProjectDTO projectDTO);

    List<Project> projectDTOsToProjects(List<ProjectDTO> projectDTOs);

    default Company companyFromId(Long id) {
        if (id == null) {
            return null;
        }
        Company company = new Company();
        company.setId(id);
        return company;
    }
}
