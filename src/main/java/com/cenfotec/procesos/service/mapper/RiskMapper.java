package com.cenfotec.procesos.service.mapper;

import com.cenfotec.procesos.domain.*;
import com.cenfotec.procesos.service.dto.RiskDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Risk and its DTO RiskDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RiskMapper {

    @Mapping(source = "project.id", target = "projectId")
    @Mapping(source = "project.name", target = "projectName")
    RiskDTO riskToRiskDTO(Risk risk);

    List<RiskDTO> risksToRiskDTOs(List<Risk> risks);

    @Mapping(source = "projectId", target = "project")
    Risk riskDTOToRisk(RiskDTO riskDTO);

    List<Risk> riskDTOsToRisks(List<RiskDTO> riskDTOs);

    default Project projectFromId(Long id) {
        if (id == null) {
            return null;
        }
        Project project = new Project();
        project.setId(id);
        return project;
    }
}
