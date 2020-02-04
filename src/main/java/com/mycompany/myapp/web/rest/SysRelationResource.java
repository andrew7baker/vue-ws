package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.SysRelation;
import com.mycompany.myapp.service.SysRelationService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.SysRelation}.
 */
@RestController
@RequestMapping("/api")
public class SysRelationResource {

    private final Logger log = LoggerFactory.getLogger(SysRelationResource.class);

    private static final String ENTITY_NAME = "sysRelation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysRelationService sysRelationService;

    public SysRelationResource(SysRelationService sysRelationService) {
        this.sysRelationService = sysRelationService;
    }

    /**
     * {@code POST  /sys-relations} : Create a new sysRelation.
     *
     * @param sysRelation the sysRelation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysRelation, or with status {@code 400 (Bad Request)} if the sysRelation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-relations")
    public ResponseEntity<SysRelation> createSysRelation(@RequestBody SysRelation sysRelation) throws URISyntaxException {
        log.debug("REST request to save SysRelation : {}", sysRelation);
        if (sysRelation.getId() != null) {
            throw new BadRequestAlertException("A new sysRelation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysRelation result = sysRelationService.save(sysRelation);
        return ResponseEntity.created(new URI("/api/sys-relations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-relations} : Updates an existing sysRelation.
     *
     * @param sysRelation the sysRelation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysRelation,
     * or with status {@code 400 (Bad Request)} if the sysRelation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysRelation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-relations")
    public ResponseEntity<SysRelation> updateSysRelation(@RequestBody SysRelation sysRelation) throws URISyntaxException {
        log.debug("REST request to update SysRelation : {}", sysRelation);
        if (sysRelation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysRelation result = sysRelationService.save(sysRelation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sysRelation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-relations} : get all the sysRelations.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysRelations in body.
     */
    @GetMapping("/sys-relations")
    public ResponseEntity<List<SysRelation>> getAllSysRelations(Pageable pageable) {
        log.debug("REST request to get a page of SysRelations");
        Page<SysRelation> page = sysRelationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-relations/:id} : get the "id" sysRelation.
     *
     * @param id the id of the sysRelation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysRelation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-relations/{id}")
    public ResponseEntity<SysRelation> getSysRelation(@PathVariable Long id) {
        log.debug("REST request to get SysRelation : {}", id);
        Optional<SysRelation> sysRelation = sysRelationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysRelation);
    }

    /**
     * {@code DELETE  /sys-relations/:id} : delete the "id" sysRelation.
     *
     * @param id the id of the sysRelation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-relations/{id}")
    public ResponseEntity<Void> deleteSysRelation(@PathVariable Long id) {
        log.debug("REST request to delete SysRelation : {}", id);
        sysRelationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
