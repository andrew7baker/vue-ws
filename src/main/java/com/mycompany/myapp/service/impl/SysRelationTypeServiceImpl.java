package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.SysRelationTypeService;
import com.mycompany.myapp.domain.SysRelationType;
import com.mycompany.myapp.repository.SysRelationTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SysRelationType}.
 */
@Service
@Transactional
public class SysRelationTypeServiceImpl implements SysRelationTypeService {

    private final Logger log = LoggerFactory.getLogger(SysRelationTypeServiceImpl.class);

    private final SysRelationTypeRepository sysRelationTypeRepository;

    public SysRelationTypeServiceImpl(SysRelationTypeRepository sysRelationTypeRepository) {
        this.sysRelationTypeRepository = sysRelationTypeRepository;
    }

    /**
     * Save a sysRelationType.
     *
     * @param sysRelationType the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SysRelationType save(SysRelationType sysRelationType) {
        log.debug("Request to save SysRelationType : {}", sysRelationType);
        return sysRelationTypeRepository.save(sysRelationType);
    }

    /**
     * Get all the sysRelationTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysRelationType> findAll(Pageable pageable) {
        log.debug("Request to get all SysRelationTypes");
        return sysRelationTypeRepository.findAll(pageable);
    }


    /**
     * Get one sysRelationType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysRelationType> findOne(Long id) {
        log.debug("Request to get SysRelationType : {}", id);
        return sysRelationTypeRepository.findById(id);
    }

    /**
     * Delete the sysRelationType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysRelationType : {}", id);
        sysRelationTypeRepository.deleteById(id);
    }
}
