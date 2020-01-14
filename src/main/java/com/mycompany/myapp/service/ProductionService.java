package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Production;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Production}.
 */
public interface ProductionService {

    /**
     * Save a production.
     *
     * @param production the entity to save.
     * @return the persisted entity.
     */
    Production save(Production production);

    /**
     * Get all the productions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Production> findAll(Pageable pageable);


    /**
     * Get the "id" production.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Production> findOne(Long id);

    /**
     * Delete the "id" production.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
