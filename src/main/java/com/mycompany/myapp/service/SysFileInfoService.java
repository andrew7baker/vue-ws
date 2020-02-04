package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.SysFileInfo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link SysFileInfo}.
 */
public interface SysFileInfoService {

    /**
     * Save a sysFileInfo.
     *
     * @param sysFileInfo the entity to save.
     * @return the persisted entity.
     */
    SysFileInfo save(SysFileInfo sysFileInfo);

    /**
     * Get all the sysFileInfos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysFileInfo> findAll(Pageable pageable);


    /**
     * Get the "id" sysFileInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysFileInfo> findOne(Long id);

    /**
     * Delete the "id" sysFileInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
