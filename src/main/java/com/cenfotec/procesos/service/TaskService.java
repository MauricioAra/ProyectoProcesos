package com.cenfotec.procesos.service;

import com.cenfotec.procesos.domain.Task;
import com.cenfotec.procesos.repository.TaskRepository;
import com.cenfotec.procesos.service.dto.TaskDTO;
import com.cenfotec.procesos.service.mapper.TaskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Task.
 */
@Service
@Transactional
public class TaskService {

    private final Logger log = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    /**
     * Save a task.
     *
     * @param taskDTO the entity to save
     * @return the persisted entity
     */
    public TaskDTO save(TaskDTO taskDTO) {
        log.debug("Request to save Task : {}", taskDTO);
        Task task = taskMapper.taskDTOToTask(taskDTO);
        task = taskRepository.save(task);
        TaskDTO result = taskMapper.taskToTaskDTO(task);
        return result;
    }

    /**
     *  Get all the tasks.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<TaskDTO> findAll() {
        log.debug("Request to get all Tasks");
        List<TaskDTO> result = taskRepository.findAll().stream()
            .map(taskMapper::taskToTaskDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }


    /**
     *  Get all the tasks.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<TaskDTO> findByProjectId(Long id) {
        List<TaskDTO> taskDTOs = new ArrayList<>();
        List<Task> tasks = taskRepository.findByProjectId(id);

        for(int i = 0; i < tasks.size(); i++){
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setId(tasks.get(i).getId());
            taskDTO.setName(tasks.get(i).getName());
            taskDTO.setDescription(tasks.get(i).getDescription());
            taskDTO.setCost(tasks.get(i).getCost());
            taskDTO.setStatus(tasks.get(i).isStatus());
            taskDTO.setCost(tasks.get(i).getCost());
            taskDTO.setTime(tasks.get(i).getTime());
            taskDTO.setRealHour(tasks.get(i).getRealHour());
            taskDTO.setProjectId(tasks.get(i).getProject().getId());
            taskDTOs.add(taskDTO);
        }
        return taskDTOs;
    }

    /**
     *  Get one task by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public TaskDTO findOne(Long id) {
        log.debug("Request to get Task : {}", id);
        Task task = taskRepository.findOne(id);
        TaskDTO taskDTO = taskMapper.taskToTaskDTO(task);
        return taskDTO;
    }

    /**
     *  Delete the  task by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Task : {}", id);
        taskRepository.delete(id);
    }
}
