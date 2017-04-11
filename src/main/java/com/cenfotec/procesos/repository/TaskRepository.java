package com.cenfotec.procesos.repository;

import com.cenfotec.procesos.domain.Task;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Task entity.
 */
@SuppressWarnings("unused")
public interface TaskRepository extends JpaRepository<Task,Long> {
    public List<Task> findByProjectId(Long id);

}
