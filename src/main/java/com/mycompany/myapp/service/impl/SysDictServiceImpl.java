package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.SysDictService;
import com.mycompany.myapp.domain.SysDict;
import com.mycompany.myapp.repository.SysDictRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SysDict}.
 */
@Service
@Transactional
public class SysDictServiceImpl implements SysDictService {

    private final Logger log = LoggerFactory.getLogger(SysDictServiceImpl.class);

    private final SysDictRepository sysDictRepository;

    public SysDictServiceImpl(SysDictRepository sysDictRepository) {
        this.sysDictRepository = sysDictRepository;
    }

    /**
     * Save a sysDict.
     *
     * @param sysDict the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SysDict save(SysDict sysDict) {
        log.debug("Request to save SysDict : {}", sysDict);
        return sysDictRepository.save(sysDict);
    }

    /**
     * Get all the sysDicts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysDict> findAll(Pageable pageable) {
        log.debug("Request to get all SysDicts");
        return sysDictRepository.findAll(pageable);
    }


    /**
     * Get one sysDict by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysDict> findOne(Long id) {
        log.debug("Request to get SysDict : {}", id);
        return sysDictRepository.findById(id);
    }

    /**
     * Delete the sysDict by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysDict : {}", id);
        sysDictRepository.deleteById(id);
    }
}
