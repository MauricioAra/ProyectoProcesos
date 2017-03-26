package com.cenfotec.procesos.service;

import com.cenfotec.procesos.domain.Risk;
import com.cenfotec.procesos.repository.RiskRepository;
import com.cenfotec.procesos.service.dto.RiskDTO;
import com.cenfotec.procesos.service.mapper.RiskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Risk.
 */
@Service
@Transactional
public class RiskService {

    private final Logger log = LoggerFactory.getLogger(RiskService.class);
    
    private final RiskRepository riskRepository;

    private final RiskMapper riskMapper;

    public RiskService(RiskRepository riskRepository, RiskMapper riskMapper) {
        this.riskRepository = riskRepository;
        this.riskMapper = riskMapper;
    }

    /**
     * Save a risk.
     *
     * @param riskDTO the entity to save
     * @return the persisted entity
     */
    public RiskDTO save(RiskDTO riskDTO) {
        log.debug("Request to save Risk : {}", riskDTO);
        Risk risk = riskMapper.riskDTOToRisk(riskDTO);
        risk = riskRepository.save(risk);
        RiskDTO result = riskMapper.riskToRiskDTO(risk);
        return result;
    }

    /**
     *  Get all the risks.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<RiskDTO> findAll() {
        log.debug("Request to get all Risks");
        List<RiskDTO> result = riskRepository.findAll().stream()
            .map(riskMapper::riskToRiskDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one risk by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public RiskDTO findOne(Long id) {
        log.debug("Request to get Risk : {}", id);
        Risk risk = riskRepository.findOne(id);
        RiskDTO riskDTO = riskMapper.riskToRiskDTO(risk);
        return riskDTO;
    }

    /**
     *  Delete the  risk by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Risk : {}", id);
        riskRepository.delete(id);
    }
}
