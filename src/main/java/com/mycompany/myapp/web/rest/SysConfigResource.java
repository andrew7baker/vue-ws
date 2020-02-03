package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.SysConfig;
import com.mycompany.myapp.service.SysConfigService;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.SysConfig}.
 */
@RestController
@RequestMapping("/api")
public class SysConfigResource {

    private final Logger log = LoggerFactory.getLogger(SysConfigResource.class);

    private static final String ENTITY_NAME = "sysConfig";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysConfigService sysConfigService;

    public SysConfigResource(SysConfigService sysConfigService) {
        this.sysConfigService = sysConfigService;
    }

    /**
     * {@code POST  /sys-configs} : Create a new sysConfig.
     *
     * @param sysConfig the sysConfig to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysConfig, or with status {@code 400 (Bad Request)} if the sysConfig has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-configs")
    public ResponseEntity<SysConfig> createSysConfig(@RequestBody SysConfig sysConfig) throws URISyntaxException {
        log.debug("REST request to save SysConfig : {}", sysConfig);
        if (sysConfig.getId() != null) {
            throw new BadRequestAlertException("A new sysConfig cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysConfig result = sysConfigService.save(sysConfig);
        return ResponseEntity.created(new URI("/api/sys-configs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-configs} : Updates an existing sysConfig.
     *
     * @param sysConfig the sysConfig to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysConfig,
     * or with status {@code 400 (Bad Request)} if the sysConfig is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysConfig couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-configs")
    public ResponseEntity<SysConfig> updateSysConfig(@RequestBody SysConfig sysConfig) throws URISyntaxException {
        log.debug("REST request to update SysConfig : {}", sysConfig);
        if (sysConfig.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysConfig result = sysConfigService.save(sysConfig);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sysConfig.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-configs} : get all the sysConfigs.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysConfigs in body.
     */
    @GetMapping("/sys-configs")
    public ResponseEntity<List<SysConfig>> getAllSysConfigs(Pageable pageable) {
        log.debug("REST request to get a page of SysConfigs");
        Page<SysConfig> page = sysConfigService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-configs/:id} : get the "id" sysConfig.
     *
     * @param id the id of the sysConfig to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysConfig, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-configs/{id}")
    public ResponseEntity<SysConfig> getSysConfig(@PathVariable Long id) {
        log.debug("REST request to get SysConfig : {}", id);
        Optional<SysConfig> sysConfig = sysConfigService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysConfig);
    }

    /**
     * {@code DELETE  /sys-configs/:id} : delete the "id" sysConfig.
     *
     * @param id the id of the sysConfig to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-configs/{id}")
    public ResponseEntity<Void> deleteSysConfig(@PathVariable Long id) {
        log.debug("REST request to delete SysConfig : {}", id);
        sysConfigService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
