package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.SysRelationType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link SysRelationType}.
 */
public interface SysRelationTypeService {

    /**
     * Save a sysRelationType.
     *
     * @param sysRelationType the entity to save.
     * @return the persisted entity.
     */
    SysRelationType save(SysRelationType sysRelationType);

    /**
     * Get all the sysRelationTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysRelationType> findAll(Pageable pageable);


    /**
     * Get the "id" sysRelationType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysRelationType> findOne(Long id);

    /**
     * Delete the "id" sysRelationType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
