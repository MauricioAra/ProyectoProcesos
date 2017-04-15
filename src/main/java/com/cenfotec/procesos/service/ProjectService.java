package com.cenfotec.procesos.service;

import com.cenfotec.procesos.domain.Project;
import com.cenfotec.procesos.domain.Task;
import com.cenfotec.procesos.repository.ProjectRepository;
import com.cenfotec.procesos.service.dto.CalculationsDTO;
import com.cenfotec.procesos.service.dto.ProjectDTO;
import com.cenfotec.procesos.service.mapper.ProjectMapper;
import com.cenfotec.procesos.service.util.PMCalculationsUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Project.
 */
@Service
@Transactional
public class ProjectService {

    private final Logger log = LoggerFactory.getLogger(ProjectService.class);
    
    private final ProjectRepository projectRepository;

    private final ProjectMapper projectMapper;

    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    /**
     * Save a project.
     *
     * @param projectDTO the entity to save
     * @return the persisted entity
     */
    public ProjectDTO save(ProjectDTO projectDTO) {
        log.debug("Request to save Project : {}", projectDTO);
        Project project = projectMapper.projectDTOToProject(projectDTO);
        project = projectRepository.save(project);
        ProjectDTO result = projectMapper.projectToProjectDTO(project);
        return result;
    }

    /**
     *  Get all the projects.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ProjectDTO> findAll() {
        log.debug("Request to get all Projects");
        List<ProjectDTO> result = projectRepository.findAll().stream()
            .map(projectMapper::projectToProjectDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one project by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public ProjectDTO findOne(Long id) {
        log.debug("Request to get Project : {}", id);
        Project project = projectRepository.findOne(id);
        ProjectDTO projectDTO = projectMapper.projectToProjectDTO(project);
        return projectDTO;
    }

    /**
     *  Delete the  project by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Project : {}", id);
        projectRepository.delete(id);
    }
    /**
     * Gets all the necessary information to perform calculations
     *  from the tasks and project
     * 
     */
    public CalculationsDTO projectCalculations(Long id) {
    	
    	CalculationsDTO calculationsDTO = new CalculationsDTO();
    	Project project =  projectRepository.findOne(id);
    	Set<Task> tasks = project.getTasks();
    	double costPerHour = PMCalculationsUtil.hourlyRateAverage(tasks);
    	double totalPlannedHours = PMCalculationsUtil.totalPlannedHours(tasks);
    	double projectBudget = PMCalculationsUtil.projectBudget(tasks);
    	double projectCompletionPercent = PMCalculationsUtil.projectCompletionPercent(tasks);
    	double hoursToDate = PMCalculationsUtil.hoursToDate(tasks);
    	
    	calculationsDTO.setPlannedValue(PMCalculationsUtil.plannedValue(costPerHour, totalPlannedHours));
    	calculationsDTO.setEarnedValue(PMCalculationsUtil.earnedValue(projectBudget, projectCompletionPercent));
    	calculationsDTO.setActualCost(PMCalculationsUtil.actualCost(costPerHour, hoursToDate));
    	calculationsDTO.setScheduleVariance(PMCalculationsUtil.
    			scheduleVariance(calculationsDTO.getEarnedValue(), calculationsDTO.getPlannedValue()));
    	calculationsDTO.setCostVariance(PMCalculationsUtil.
    			costVariance(calculationsDTO.getEarnedValue(), calculationsDTO.getActualCost()));
    	calculationsDTO.setSpi(PMCalculationsUtil.SPI(calculationsDTO.getEarnedValue(), calculationsDTO.getPlannedValue()));
    	calculationsDTO.setCpi(PMCalculationsUtil.CPI(calculationsDTO.getEarnedValue(), calculationsDTO.getActualCost()));
    	calculationsDTO.setEac(PMCalculationsUtil.EAC(calculationsDTO.getPlannedValue(), calculationsDTO.getCpi()));
    	
    	
    	return calculationsDTO;
    }
}
