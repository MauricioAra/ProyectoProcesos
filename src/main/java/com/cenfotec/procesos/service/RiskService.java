package com.cenfotec.procesos.service;

import com.cenfotec.procesos.domain.Risk;
import com.cenfotec.procesos.repository.RiskRepository;
import com.cenfotec.procesos.service.dto.MatrixDTO;
import com.cenfotec.procesos.service.dto.RiskDTO;
import com.cenfotec.procesos.service.dto.TagDTO;
import com.cenfotec.procesos.service.mapper.RiskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import springfox.documentation.service.Tag;

import java.util.ArrayList;
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
     * Save a risk.
     *
     * @return the persisted entity
     */
    public List<RiskDTO> findAllByProjectId(Long id) {
        List<RiskDTO> riskDTOs = new ArrayList<>();
        List<Risk> risks = riskRepository.findByProjectId(id);

        for (int i = 0; i < risks.size(); i++){
            RiskDTO riskDTO = new RiskDTO();

            riskDTO.setId(risks.get(i).getId());
            riskDTO.setName(risks.get(i).getName());
            riskDTO.setImpact(risks.get(i).getImpact());
            riskDTO.setProbability(risks.get(i).getProbability());
            riskDTO.setProjectId(risks.get(i).getProject().getId());
            riskDTO.setTag(risks.get(i).getTag());
            riskDTOs.add(riskDTO);
        }

        return riskDTOs;
    }


    public MatrixDTO createMatrix(Long id){
        MatrixDTO matrixDTO = new MatrixDTO();
        List<Risk> risks = riskRepository.findByProjectId(id);

        List<TagDTO> A1s = new ArrayList<>();
        List<TagDTO> A2s = new ArrayList<>();
        List<TagDTO> A3s = new ArrayList<>();
        List<TagDTO> A4s = new ArrayList<>();
        List<TagDTO> A5s = new ArrayList<>();
        List<TagDTO> B1s = new ArrayList<>();
        List<TagDTO> B2s = new ArrayList<>();
        List<TagDTO> B3s = new ArrayList<>();
        List<TagDTO> B4s = new ArrayList<>();
        List<TagDTO> B5s = new ArrayList<>();
        List<TagDTO> C1s = new ArrayList<>();
        List<TagDTO> C2s = new ArrayList<>();
        List<TagDTO> C3s = new ArrayList<>();
        List<TagDTO> C4s = new ArrayList<>();
        List<TagDTO> C5s = new ArrayList<>();
        List<TagDTO> D1s = new ArrayList<>();
        List<TagDTO> D2s = new ArrayList<>();
        List<TagDTO> D3s = new ArrayList<>();
        List<TagDTO> D4s = new ArrayList<>();
        List<TagDTO> D5s = new ArrayList<>();


        for(int i = 0; i < risks.size();i++){
            TagDTO tagDTO = new TagDTO();

            if(risks.get(i).getTag().equals("A1")){
                tagDTO.setId(risks.get(i).getId());
                tagDTO.setName(risks.get(i).getName());
                tagDTO.setProbability(risks.get(i).getProbability());
                tagDTO.setImpact(risks.get(i).getImpact());
                tagDTO.setTag(risks.get(i).getTag());
                A1s.add(tagDTO);
            }
            if(risks.get(i).getTag().equals("A2")){
                tagDTO.setId(risks.get(i).getId());
                tagDTO.setName(risks.get(i).getName());
                tagDTO.setProbability(risks.get(i).getProbability());
                tagDTO.setImpact(risks.get(i).getImpact());
                tagDTO.setTag(risks.get(i).getTag());
                A2s.add(tagDTO);
            }
            if(risks.get(i).getTag().equals("A3")){
                tagDTO.setId(risks.get(i).getId());
                tagDTO.setName(risks.get(i).getName());
                tagDTO.setProbability(risks.get(i).getProbability());
                tagDTO.setImpact(risks.get(i).getImpact());
                tagDTO.setTag(risks.get(i).getTag());
                A3s.add(tagDTO);
            }
            if(risks.get(i).getTag().equals("A4")){
                tagDTO.setId(risks.get(i).getId());
                tagDTO.setName(risks.get(i).getName());
                tagDTO.setProbability(risks.get(i).getProbability());
                tagDTO.setImpact(risks.get(i).getImpact());
                tagDTO.setTag(risks.get(i).getTag());
                A4s.add(tagDTO);
            }
            if(risks.get(i).getTag().equals("A5")){
                tagDTO.setId(risks.get(i).getId());
                tagDTO.setName(risks.get(i).getName());
                tagDTO.setProbability(risks.get(i).getProbability());
                tagDTO.setImpact(risks.get(i).getImpact());
                tagDTO.setTag(risks.get(i).getTag());
                A5s.add(tagDTO);
            }
            if(risks.get(i).getTag().equals("B1")){
                tagDTO.setId(risks.get(i).getId());
                tagDTO.setName(risks.get(i).getName());
                tagDTO.setProbability(risks.get(i).getProbability());
                tagDTO.setImpact(risks.get(i).getImpact());
                tagDTO.setTag(risks.get(i).getTag());
                B1s.add(tagDTO);
            }
            if(risks.get(i).getTag().equals("B2")){
                tagDTO.setId(risks.get(i).getId());
                tagDTO.setName(risks.get(i).getName());
                tagDTO.setProbability(risks.get(i).getProbability());
                tagDTO.setImpact(risks.get(i).getImpact());
                tagDTO.setTag(risks.get(i).getTag());
                B2s.add(tagDTO);
            }
            if(risks.get(i).getTag().equals("B3")){
                tagDTO.setId(risks.get(i).getId());
                tagDTO.setName(risks.get(i).getName());
                tagDTO.setProbability(risks.get(i).getProbability());
                tagDTO.setImpact(risks.get(i).getImpact());
                tagDTO.setTag(risks.get(i).getTag());
                B3s.add(tagDTO);
            }

            if(risks.get(i).getTag().equals("B4")){
                tagDTO.setId(risks.get(i).getId());
                tagDTO.setName(risks.get(i).getName());
                tagDTO.setProbability(risks.get(i).getProbability());
                tagDTO.setImpact(risks.get(i).getImpact());
                tagDTO.setTag(risks.get(i).getTag());
                B4s.add(tagDTO);
            }
            if(risks.get(i).getTag().equals("B5")){
                tagDTO.setId(risks.get(i).getId());
                tagDTO.setName(risks.get(i).getName());
                tagDTO.setProbability(risks.get(i).getProbability());
                tagDTO.setImpact(risks.get(i).getImpact());
                tagDTO.setTag(risks.get(i).getTag());
                B5s.add(tagDTO);
            }
            if(risks.get(i).getTag().equals("C1")){
                tagDTO.setId(risks.get(i).getId());
                tagDTO.setName(risks.get(i).getName());
                tagDTO.setProbability(risks.get(i).getProbability());
                tagDTO.setImpact(risks.get(i).getImpact());
                tagDTO.setTag(risks.get(i).getTag());
                C1s.add(tagDTO);
            }
            if(risks.get(i).getTag().equals("C2")){
                tagDTO.setId(risks.get(i).getId());
                tagDTO.setName(risks.get(i).getName());
                tagDTO.setProbability(risks.get(i).getProbability());
                tagDTO.setImpact(risks.get(i).getImpact());
                tagDTO.setTag(risks.get(i).getTag());
                C2s.add(tagDTO);
            }
            if(risks.get(i).getTag().equals("C3")){
                tagDTO.setId(risks.get(i).getId());
                tagDTO.setName(risks.get(i).getName());
                tagDTO.setProbability(risks.get(i).getProbability());
                tagDTO.setImpact(risks.get(i).getImpact());
                tagDTO.setTag(risks.get(i).getTag());
                C3s.add(tagDTO);
            }
            if(risks.get(i).getTag().equals("C4")){
                tagDTO.setId(risks.get(i).getId());
                tagDTO.setName(risks.get(i).getName());
                tagDTO.setProbability(risks.get(i).getProbability());
                tagDTO.setImpact(risks.get(i).getImpact());
                tagDTO.setTag(risks.get(i).getTag());
                C4s.add(tagDTO);
            }
            if(risks.get(i).getTag().equals("C5")){
                tagDTO.setId(risks.get(i).getId());
                tagDTO.setName(risks.get(i).getName());
                tagDTO.setProbability(risks.get(i).getProbability());
                tagDTO.setImpact(risks.get(i).getImpact());
                tagDTO.setTag(risks.get(i).getTag());
                C5s.add(tagDTO);
            }
            if(risks.get(i).getTag().equals("D1")){
                tagDTO.setId(risks.get(i).getId());
                tagDTO.setName(risks.get(i).getName());
                tagDTO.setProbability(risks.get(i).getProbability());
                tagDTO.setImpact(risks.get(i).getImpact());
                tagDTO.setTag(risks.get(i).getTag());
                D1s.add(tagDTO);
            }
            if(risks.get(i).getTag().equals("D2")){
                tagDTO.setId(risks.get(i).getId());
                tagDTO.setName(risks.get(i).getName());
                tagDTO.setProbability(risks.get(i).getProbability());
                tagDTO.setImpact(risks.get(i).getImpact());
                tagDTO.setTag(risks.get(i).getTag());
                D2s.add(tagDTO);
            }
            if(risks.get(i).getTag().equals("D3")){
                tagDTO.setId(risks.get(i).getId());
                tagDTO.setName(risks.get(i).getName());
                tagDTO.setProbability(risks.get(i).getProbability());
                tagDTO.setImpact(risks.get(i).getImpact());
                tagDTO.setTag(risks.get(i).getTag());
                D3s.add(tagDTO);
            }
            if(risks.get(i).getTag().equals("D4")){
                tagDTO.setId(risks.get(i).getId());
                tagDTO.setName(risks.get(i).getName());
                tagDTO.setProbability(risks.get(i).getProbability());
                tagDTO.setImpact(risks.get(i).getImpact());
                tagDTO.setTag(risks.get(i).getTag());
                D4s.add(tagDTO);
            }
            if(risks.get(i).getTag().equals("D5")){
                tagDTO.setId(risks.get(i).getId());
                tagDTO.setName(risks.get(i).getName());
                tagDTO.setProbability(risks.get(i).getProbability());
                tagDTO.setImpact(risks.get(i).getImpact());
                tagDTO.setTag(risks.get(i).getTag());
                D5s.add(tagDTO);
            }
        }

        matrixDTO.setA1(A1s);
        matrixDTO.setA2(A2s);
        matrixDTO.setA3(A3s);
        matrixDTO.setA4(A4s);
        matrixDTO.setA5(A5s);
        matrixDTO.setB1(B1s);
        matrixDTO.setB2(B2s);
        matrixDTO.setB3(B3s);
        matrixDTO.setB4(B4s);
        matrixDTO.setB5(B5s);
        matrixDTO.setC1(C1s);
        matrixDTO.setC2(C2s);
        matrixDTO.setC3(C3s);
        matrixDTO.setC4(C4s);
        matrixDTO.setC5(C5s);
        matrixDTO.setD1(D1s);
        matrixDTO.setD2(D2s);
        matrixDTO.setD3(D3s);
        matrixDTO.setD4(D4s);
        matrixDTO.setD5(D5s);

        return matrixDTO;
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
