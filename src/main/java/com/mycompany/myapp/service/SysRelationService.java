package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.SysRelation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link SysRelation}.
 */
public interface SysRelationService {

    /**
     * Save a sysRelation.
     *
     * @param sysRelation the entity to save.
     * @return the persisted entity.
     */
    SysRelation save(SysRelation sysRelation);

    /**
     * Get all the sysRelations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysRelation> findAll(Pageable pageable);


    /**
     * Get the "id" sysRelation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysRelation> findOne(Long id);

    /**
     * Delete the "id" sysRelation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
