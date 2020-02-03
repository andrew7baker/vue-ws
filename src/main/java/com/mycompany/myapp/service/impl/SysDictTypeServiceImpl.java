package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.SysDictTypeService;
import com.mycompany.myapp.domain.SysDictType;
import com.mycompany.myapp.repository.SysDictTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SysDictType}.
 */
@Service
@Transactional
public class SysDictTypeServiceImpl implements SysDictTypeService {

    private final Logger log = LoggerFactory.getLogger(SysDictTypeServiceImpl.class);

    private final SysDictTypeRepository sysDictTypeRepository;

    public SysDictTypeServiceImpl(SysDictTypeRepository sysDictTypeRepository) {
        this.sysDictTypeRepository = sysDictTypeRepository;
    }

    /**
     * Save a sysDictType.
     *
     * @param sysDictType the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SysDictType save(SysDictType sysDictType) {
        log.debug("Request to save SysDictType : {}", sysDictType);
        return sysDictTypeRepository.save(sysDictType);
    }

    /**
     * Get all the sysDictTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysDictType> findAll(Pageable pageable) {
        log.debug("Request to get all SysDictTypes");
        return sysDictTypeRepository.findAll(pageable);
    }


    /**
     * Get one sysDictType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysDictType> findOne(Long id) {
        log.debug("Request to get SysDictType : {}", id);
        return sysDictTypeRepository.findById(id);
    }

    /**
     * Delete the sysDictType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysDictType : {}", id);
        sysDictTypeRepository.deleteById(id);
    }
}
