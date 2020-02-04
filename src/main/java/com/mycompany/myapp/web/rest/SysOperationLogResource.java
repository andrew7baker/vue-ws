package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.SysOperationLog;
import com.mycompany.myapp.service.SysOperationLogService;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.SysOperationLog}.
 */
@RestController
@RequestMapping("/api")
public class SysOperationLogResource {

    private final Logger log = LoggerFactory.getLogger(SysOperationLogResource.class);

    private static final String ENTITY_NAME = "sysOperationLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysOperationLogService sysOperationLogService;

    public SysOperationLogResource(SysOperationLogService sysOperationLogService) {
        this.sysOperationLogService = sysOperationLogService;
    }

    /**
     * {@code POST  /sys-operation-logs} : Create a new sysOperationLog.
     *
     * @param sysOperationLog the sysOperationLog to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysOperationLog, or with status {@code 400 (Bad Request)} if the sysOperationLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-operation-logs")
    public ResponseEntity<SysOperationLog> createSysOperationLog(@RequestBody SysOperationLog sysOperationLog) throws URISyntaxException {
        log.debug("REST request to save SysOperationLog : {}", sysOperationLog);
        if (sysOperationLog.getId() != null) {
            throw new BadRequestAlertException("A new sysOperationLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysOperationLog result = sysOperationLogService.save(sysOperationLog);
        return ResponseEntity.created(new URI("/api/sys-operation-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-operation-logs} : Updates an existing sysOperationLog.
     *
     * @param sysOperationLog the sysOperationLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysOperationLog,
     * or with status {@code 400 (Bad Request)} if the sysOperationLog is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysOperationLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-operation-logs")
    public ResponseEntity<SysOperationLog> updateSysOperationLog(@RequestBody SysOperationLog sysOperationLog) throws URISyntaxException {
        log.debug("REST request to update SysOperationLog : {}", sysOperationLog);
        if (sysOperationLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysOperationLog result = sysOperationLogService.save(sysOperationLog);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sysOperationLog.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-operation-logs} : get all the sysOperationLogs.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysOperationLogs in body.
     */
    @GetMapping("/sys-operation-logs")
    public ResponseEntity<List<SysOperationLog>> getAllSysOperationLogs(Pageable pageable) {
        log.debug("REST request to get a page of SysOperationLogs");
        Page<SysOperationLog> page = sysOperationLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-operation-logs/:id} : get the "id" sysOperationLog.
     *
     * @param id the id of the sysOperationLog to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysOperationLog, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-operation-logs/{id}")
    public ResponseEntity<SysOperationLog> getSysOperationLog(@PathVariable Long id) {
        log.debug("REST request to get SysOperationLog : {}", id);
        Optional<SysOperationLog> sysOperationLog = sysOperationLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysOperationLog);
    }

    /**
     * {@code DELETE  /sys-operation-logs/:id} : delete the "id" sysOperationLog.
     *
     * @param id the id of the sysOperationLog to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-operation-logs/{id}")
    public ResponseEntity<Void> deleteSysOperationLog(@PathVariable Long id) {
        log.debug("REST request to delete SysOperationLog : {}", id);
        sysOperationLogService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
