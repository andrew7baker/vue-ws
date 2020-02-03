package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.SysDeptService;
import com.mycompany.myapp.domain.SysDept;
import com.mycompany.myapp.repository.SysDeptRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SysDept}.
 */
@Service
@Transactional
public class SysDeptServiceImpl implements SysDeptService {

    private final Logger log = LoggerFactory.getLogger(SysDeptServiceImpl.class);

    private final SysDeptRepository sysDeptRepository;

    public SysDeptServiceImpl(SysDeptRepository sysDeptRepository) {
        this.sysDeptRepository = sysDeptRepository;
    }

    /**
     * Save a sysDept.
     *
     * @param sysDept the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SysDept save(SysDept sysDept) {
        log.debug("Request to save SysDept : {}", sysDept);
        return sysDeptRepository.save(sysDept);
    }

    /**
     * Get all the sysDepts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysDept> findAll(Pageable pageable) {
        log.debug("Request to get all SysDepts");
        return sysDeptRepository.findAll(pageable);
    }


    /**
     * Get one sysDept by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysDept> findOne(Long id) {
        log.debug("Request to get SysDept : {}", id);
        return sysDeptRepository.findById(id);
    }

    /**
     * Delete the sysDept by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysDept : {}", id);
        sysDeptRepository.deleteById(id);
    }
}
