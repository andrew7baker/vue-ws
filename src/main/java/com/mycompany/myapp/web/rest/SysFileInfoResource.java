package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.SysFileInfo;
import com.mycompany.myapp.service.SysFileInfoService;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.SysFileInfo}.
 */
@RestController
@RequestMapping("/api")
public class SysFileInfoResource {

    private final Logger log = LoggerFactory.getLogger(SysFileInfoResource.class);

    private static final String ENTITY_NAME = "sysFileInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysFileInfoService sysFileInfoService;

    public SysFileInfoResource(SysFileInfoService sysFileInfoService) {
        this.sysFileInfoService = sysFileInfoService;
    }

    /**
     * {@code POST  /sys-file-infos} : Create a new sysFileInfo.
     *
     * @param sysFileInfo the sysFileInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysFileInfo, or with status {@code 400 (Bad Request)} if the sysFileInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-file-infos")
    public ResponseEntity<SysFileInfo> createSysFileInfo(@RequestBody SysFileInfo sysFileInfo) throws URISyntaxException {
        log.debug("REST request to save SysFileInfo : {}", sysFileInfo);
        if (sysFileInfo.getId() != null) {
            throw new BadRequestAlertException("A new sysFileInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysFileInfo result = sysFileInfoService.save(sysFileInfo);
        return ResponseEntity.created(new URI("/api/sys-file-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-file-infos} : Updates an existing sysFileInfo.
     *
     * @param sysFileInfo the sysFileInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysFileInfo,
     * or with status {@code 400 (Bad Request)} if the sysFileInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysFileInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-file-infos")
    public ResponseEntity<SysFileInfo> updateSysFileInfo(@RequestBody SysFileInfo sysFileInfo) throws URISyntaxException {
        log.debug("REST request to update SysFileInfo : {}", sysFileInfo);
        if (sysFileInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysFileInfo result = sysFileInfoService.save(sysFileInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sysFileInfo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-file-infos} : get all the sysFileInfos.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysFileInfos in body.
     */
    @GetMapping("/sys-file-infos")
    public ResponseEntity<List<SysFileInfo>> getAllSysFileInfos(Pageable pageable) {
        log.debug("REST request to get a page of SysFileInfos");
        Page<SysFileInfo> page = sysFileInfoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-file-infos/:id} : get the "id" sysFileInfo.
     *
     * @param id the id of the sysFileInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysFileInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-file-infos/{id}")
    public ResponseEntity<SysFileInfo> getSysFileInfo(@PathVariable Long id) {
        log.debug("REST request to get SysFileInfo : {}", id);
        Optional<SysFileInfo> sysFileInfo = sysFileInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysFileInfo);
    }

    /**
     * {@code DELETE  /sys-file-infos/:id} : delete the "id" sysFileInfo.
     *
     * @param id the id of the sysFileInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-file-infos/{id}")
    public ResponseEntity<Void> deleteSysFileInfo(@PathVariable Long id) {
        log.debug("REST request to delete SysFileInfo : {}", id);
        sysFileInfoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
