package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.SysRelationType;
import com.mycompany.myapp.service.SysRelationTypeService;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.SysRelationType}.
 */
@RestController
@RequestMapping("/api")
public class SysRelationTypeResource {

    private final Logger log = LoggerFactory.getLogger(SysRelationTypeResource.class);

    private static final String ENTITY_NAME = "sysRelationType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysRelationTypeService sysRelationTypeService;

    public SysRelationTypeResource(SysRelationTypeService sysRelationTypeService) {
        this.sysRelationTypeService = sysRelationTypeService;
    }

    /**
     * {@code POST  /sys-relation-types} : Create a new sysRelationType.
     *
     * @param sysRelationType the sysRelationType to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysRelationType, or with status {@code 400 (Bad Request)} if the sysRelationType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-relation-types")
    public ResponseEntity<SysRelationType> createSysRelationType(@RequestBody SysRelationType sysRelationType) throws URISyntaxException {
        log.debug("REST request to save SysRelationType : {}", sysRelationType);
        if (sysRelationType.getId() != null) {
            throw new BadRequestAlertException("A new sysRelationType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysRelationType result = sysRelationTypeService.save(sysRelationType);
        return ResponseEntity.created(new URI("/api/sys-relation-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-relation-types} : Updates an existing sysRelationType.
     *
     * @param sysRelationType the sysRelationType to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysRelationType,
     * or with status {@code 400 (Bad Request)} if the sysRelationType is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysRelationType couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-relation-types")
    public ResponseEntity<SysRelationType> updateSysRelationType(@RequestBody SysRelationType sysRelationType) throws URISyntaxException {
        log.debug("REST request to update SysRelationType : {}", sysRelationType);
        if (sysRelationType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysRelationType result = sysRelationTypeService.save(sysRelationType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sysRelationType.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-relation-types} : get all the sysRelationTypes.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysRelationTypes in body.
     */
    @GetMapping("/sys-relation-types")
    public ResponseEntity<List<SysRelationType>> getAllSysRelationTypes(Pageable pageable) {
        log.debug("REST request to get a page of SysRelationTypes");
        Page<SysRelationType> page = sysRelationTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-relation-types/:id} : get the "id" sysRelationType.
     *
     * @param id the id of the sysRelationType to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysRelationType, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-relation-types/{id}")
    public ResponseEntity<SysRelationType> getSysRelationType(@PathVariable Long id) {
        log.debug("REST request to get SysRelationType : {}", id);
        Optional<SysRelationType> sysRelationType = sysRelationTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysRelationType);
    }

    /**
     * {@code DELETE  /sys-relation-types/:id} : delete the "id" sysRelationType.
     *
     * @param id the id of the sysRelationType to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-relation-types/{id}")
    public ResponseEntity<Void> deleteSysRelationType(@PathVariable Long id) {
        log.debug("REST request to delete SysRelationType : {}", id);
        sysRelationTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
