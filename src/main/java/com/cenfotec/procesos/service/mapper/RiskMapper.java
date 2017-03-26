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

    RiskDTO riskToRiskDTO(Risk risk);

    List<RiskDTO> risksToRiskDTOs(List<Risk> risks);

    Risk riskDTOToRisk(RiskDTO riskDTO);

    List<Risk> riskDTOsToRisks(List<RiskDTO> riskDTOs);
}
