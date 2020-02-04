package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.SysRelationService;
import com.mycompany.myapp.domain.SysRelation;
import com.mycompany.myapp.repository.SysRelationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SysRelation}.
 */
@Service
@Transactional
public class SysRelationServiceImpl implements SysRelationService {

    private final Logger log = LoggerFactory.getLogger(SysRelationServiceImpl.class);

    private final SysRelationRepository sysRelationRepository;

    public SysRelationServiceImpl(SysRelationRepository sysRelationRepository) {
        this.sysRelationRepository = sysRelationRepository;
    }

    /**
     * Save a sysRelation.
     *
     * @param sysRelation the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SysRelation save(SysRelation sysRelation) {
        log.debug("Request to save SysRelation : {}", sysRelation);
        return sysRelationRepository.save(sysRelation);
    }

    /**
     * Get all the sysRelations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysRelation> findAll(Pageable pageable) {
        log.debug("Request to get all SysRelations");
        return sysRelationRepository.findAll(pageable);
    }


    /**
     * Get one sysRelation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysRelation> findOne(Long id) {
        log.debug("Request to get SysRelation : {}", id);
        return sysRelationRepository.findById(id);
    }

    /**
     * Delete the sysRelation by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysRelation : {}", id);
        sysRelationRepository.deleteById(id);
    }
}
