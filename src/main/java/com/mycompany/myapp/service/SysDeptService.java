package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.SysDept;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link SysDept}.
 */
public interface SysDeptService {

    /**
     * Save a sysDept.
     *
     * @param sysDept the entity to save.
     * @return the persisted entity.
     */
    SysDept save(SysDept sysDept);

    /**
     * Get all the sysDepts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysDept> findAll(Pageable pageable);


    /**
     * Get the "id" sysDept.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysDept> findOne(Long id);

    /**
     * Delete the "id" sysDept.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
