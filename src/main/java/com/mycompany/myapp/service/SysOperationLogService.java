package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.SysOperationLog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link SysOperationLog}.
 */
public interface SysOperationLogService {

    /**
     * Save a sysOperationLog.
     *
     * @param sysOperationLog the entity to save.
     * @return the persisted entity.
     */
    SysOperationLog save(SysOperationLog sysOperationLog);

    /**
     * Get all the sysOperationLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysOperationLog> findAll(Pageable pageable);


    /**
     * Get the "id" sysOperationLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysOperationLog> findOne(Long id);

    /**
     * Delete the "id" sysOperationLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
