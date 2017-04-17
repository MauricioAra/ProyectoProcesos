package com.cenfotec.procesos.service;

import com.cenfotec.procesos.domain.Company;
import com.cenfotec.procesos.domain.Project;
import com.cenfotec.procesos.repository.CompanyRepository;
import com.cenfotec.procesos.repository.ProjectRepository;
import com.cenfotec.procesos.service.dto.CompanyDTO;
import com.cenfotec.procesos.service.dto.ProjectDTO;
import com.cenfotec.procesos.service.mapper.CompanyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Company.
 */
@Service
@Transactional
public class CompanyService {

    private final Logger log = LoggerFactory.getLogger(CompanyService.class);

    private final CompanyRepository companyRepository;

    private final ProjectRepository projectRepository;

    private final CompanyMapper companyMapper;


    public CompanyService(CompanyRepository companyRepository, CompanyMapper companyMapper,ProjectRepository projectRepository) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
        this.projectRepository = projectRepository;
    }

    /**
     * Save a company.
     *
     * @param companyDTO the entity to save
     * @return the persisted entity
     */
    public CompanyDTO save(CompanyDTO companyDTO) {
        log.debug("Request to save Company : {}", companyDTO);
        Company company = companyMapper.companyDTOToCompany(companyDTO);
        company = companyRepository.save(company);
        CompanyDTO result = companyMapper.companyToCompanyDTO(company);
        return result;
    }

    /**
     *  Get all the companies.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<CompanyDTO> findAll() {
        log.debug("Request to get all Companies");
        List<CompanyDTO> result = companyRepository.findAll().stream()
            .map(companyMapper::companyToCompanyDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one company by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public CompanyDTO findOne(Long id) {
        log.debug("Request to get Company : {}", id);
        Company company = companyRepository.findOne(id);
        CompanyDTO companyDTO = companyMapper.companyToCompanyDTO(company);
        return companyDTO;
    }

    /**
     *  Get one company by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public List<ProjectDTO> findProjectsBycompanies(Long id) {
        List<Project> projects = projectRepository.findByCompanyId(id);
        List<ProjectDTO> projectDTOs = new ArrayList<>();

        for(int i = 0; i < projects.size(); i ++){
            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.setId(projects.get(i).getId());
            projectDTO.setName(projects.get(i).getName());
            projectDTO.setDescription(projects.get(i).getDescription());
            projectDTO.setTechnology(projects.get(i).getTechnology());
            projectDTO.setDevice(projects.get(i).getDevice());
            projectDTO.setCompanyId(projects.get(i).getCompany().getId());
            projectDTO.setCompanyName(projects.get(i).getCompany().getName());
            projectDTOs.add(projectDTO);
        }


        return projectDTOs;
    }

    /**
     *  Delete the  company by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Company : {}", id);
        companyRepository.delete(id);
    }
}
