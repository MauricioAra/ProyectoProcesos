package com.cenfotec.procesos.repository;

import com.cenfotec.procesos.domain.Risk;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Risk entity.
 */
@SuppressWarnings("unused")
public interface RiskRepository extends JpaRepository<Risk,Long> {

}
