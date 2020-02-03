package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.SysDict;
import com.mycompany.myapp.service.SysDictService;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.SysDict}.
 */
@RestController
@RequestMapping("/api")
public class SysDictResource {

    private final Logger log = LoggerFactory.getLogger(SysDictResource.class);

    private static final String ENTITY_NAME = "sysDict";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysDictService sysDictService;

    public SysDictResource(SysDictService sysDictService) {
        this.sysDictService = sysDictService;
    }

    /**
     * {@code POST  /sys-dicts} : Create a new sysDict.
     *
     * @param sysDict the sysDict to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysDict, or with status {@code 400 (Bad Request)} if the sysDict has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-dicts")
    public ResponseEntity<SysDict> createSysDict(@RequestBody SysDict sysDict) throws URISyntaxException {
        log.debug("REST request to save SysDict : {}", sysDict);
        if (sysDict.getId() != null) {
            throw new BadRequestAlertException("A new sysDict cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysDict result = sysDictService.save(sysDict);
        return ResponseEntity.created(new URI("/api/sys-dicts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-dicts} : Updates an existing sysDict.
     *
     * @param sysDict the sysDict to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysDict,
     * or with status {@code 400 (Bad Request)} if the sysDict is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysDict couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-dicts")
    public ResponseEntity<SysDict> updateSysDict(@RequestBody SysDict sysDict) throws URISyntaxException {
        log.debug("REST request to update SysDict : {}", sysDict);
        if (sysDict.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysDict result = sysDictService.save(sysDict);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sysDict.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-dicts} : get all the sysDicts.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysDicts in body.
     */
    @GetMapping("/sys-dicts")
    public ResponseEntity<List<SysDict>> getAllSysDicts(Pageable pageable) {
        log.debug("REST request to get a page of SysDicts");
        Page<SysDict> page = sysDictService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-dicts/:id} : get the "id" sysDict.
     *
     * @param id the id of the sysDict to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysDict, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-dicts/{id}")
    public ResponseEntity<SysDict> getSysDict(@PathVariable Long id) {
        log.debug("REST request to get SysDict : {}", id);
        Optional<SysDict> sysDict = sysDictService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysDict);
    }

    /**
     * {@code DELETE  /sys-dicts/:id} : delete the "id" sysDict.
     *
     * @param id the id of the sysDict to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-dicts/{id}")
    public ResponseEntity<Void> deleteSysDict(@PathVariable Long id) {
        log.debug("REST request to delete SysDict : {}", id);
        sysDictService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
