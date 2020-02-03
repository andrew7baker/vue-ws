package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.SysDictType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link SysDictType}.
 */
public interface SysDictTypeService {

    /**
     * Save a sysDictType.
     *
     * @param sysDictType the entity to save.
     * @return the persisted entity.
     */
    SysDictType save(SysDictType sysDictType);

    /**
     * Get all the sysDictTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysDictType> findAll(Pageable pageable);


    /**
     * Get the "id" sysDictType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysDictType> findOne(Long id);

    /**
     * Delete the "id" sysDictType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
