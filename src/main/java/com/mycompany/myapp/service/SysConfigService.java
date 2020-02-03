package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.SysConfig;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link SysConfig}.
 */
public interface SysConfigService {

    /**
     * Save a sysConfig.
     *
     * @param sysConfig the entity to save.
     * @return the persisted entity.
     */
    SysConfig save(SysConfig sysConfig);

    /**
     * Get all the sysConfigs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysConfig> findAll(Pageable pageable);


    /**
     * Get the "id" sysConfig.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysConfig> findOne(Long id);

    /**
     * Delete the "id" sysConfig.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
