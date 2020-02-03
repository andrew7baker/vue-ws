package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.SysDict;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link SysDict}.
 */
public interface SysDictService {

    /**
     * Save a sysDict.
     *
     * @param sysDict the entity to save.
     * @return the persisted entity.
     */
    SysDict save(SysDict sysDict);

    /**
     * Get all the sysDicts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysDict> findAll(Pageable pageable);


    /**
     * Get the "id" sysDict.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysDict> findOne(Long id);

    /**
     * Delete the "id" sysDict.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
