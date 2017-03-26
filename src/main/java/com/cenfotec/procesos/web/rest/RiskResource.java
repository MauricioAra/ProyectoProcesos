package com.cenfotec.procesos.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cenfotec.procesos.service.RiskService;
import com.cenfotec.procesos.web.rest.util.HeaderUtil;
import com.cenfotec.procesos.service.dto.RiskDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Risk.
 */
@RestController
@RequestMapping("/api")
public class RiskResource {

    private final Logger log = LoggerFactory.getLogger(RiskResource.class);

    private static final String ENTITY_NAME = "risk";
        
    private final RiskService riskService;

    public RiskResource(RiskService riskService) {
        this.riskService = riskService;
    }

    /**
     * POST  /risks : Create a new risk.
     *
     * @param riskDTO the riskDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new riskDTO, or with status 400 (Bad Request) if the risk has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/risks")
    @Timed
    public ResponseEntity<RiskDTO> createRisk(@Valid @RequestBody RiskDTO riskDTO) throws URISyntaxException {
        log.debug("REST request to save Risk : {}", riskDTO);
        if (riskDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new risk cannot already have an ID")).body(null);
        }
        RiskDTO result = riskService.save(riskDTO);
        return ResponseEntity.created(new URI("/api/risks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /risks : Updates an existing risk.
     *
     * @param riskDTO the riskDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated riskDTO,
     * or with status 400 (Bad Request) if the riskDTO is not valid,
     * or with status 500 (Internal Server Error) if the riskDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/risks")
    @Timed
    public ResponseEntity<RiskDTO> updateRisk(@Valid @RequestBody RiskDTO riskDTO) throws URISyntaxException {
        log.debug("REST request to update Risk : {}", riskDTO);
        if (riskDTO.getId() == null) {
            return createRisk(riskDTO);
        }
        RiskDTO result = riskService.save(riskDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, riskDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /risks : get all the risks.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of risks in body
     */
    @GetMapping("/risks")
    @Timed
    public List<RiskDTO> getAllRisks() {
        log.debug("REST request to get all Risks");
        return riskService.findAll();
    }

    /**
     * GET  /risks/:id : get the "id" risk.
     *
     * @param id the id of the riskDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the riskDTO, or with status 404 (Not Found)
     */
    @GetMapping("/risks/{id}")
    @Timed
    public ResponseEntity<RiskDTO> getRisk(@PathVariable Long id) {
        log.debug("REST request to get Risk : {}", id);
        RiskDTO riskDTO = riskService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(riskDTO));
    }

    /**
     * DELETE  /risks/:id : delete the "id" risk.
     *
     * @param id the id of the riskDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/risks/{id}")
    @Timed
    public ResponseEntity<Void> deleteRisk(@PathVariable Long id) {
        log.debug("REST request to delete Risk : {}", id);
        riskService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
