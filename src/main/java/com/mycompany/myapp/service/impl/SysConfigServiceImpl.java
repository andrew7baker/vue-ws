package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.SysConfigService;
import com.mycompany.myapp.domain.SysConfig;
import com.mycompany.myapp.repository.SysConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SysConfig}.
 */
@Service
@Transactional
public class SysConfigServiceImpl implements SysConfigService {

    private final Logger log = LoggerFactory.getLogger(SysConfigServiceImpl.class);

    private final SysConfigRepository sysConfigRepository;

    public SysConfigServiceImpl(SysConfigRepository sysConfigRepository) {
        this.sysConfigRepository = sysConfigRepository;
    }

    /**
     * Save a sysConfig.
     *
     * @param sysConfig the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SysConfig save(SysConfig sysConfig) {
        log.debug("Request to save SysConfig : {}", sysConfig);
        return sysConfigRepository.save(sysConfig);
    }

    /**
     * Get all the sysConfigs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysConfig> findAll(Pageable pageable) {
        log.debug("Request to get all SysConfigs");
        return sysConfigRepository.findAll(pageable);
    }


    /**
     * Get one sysConfig by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysConfig> findOne(Long id) {
        log.debug("Request to get SysConfig : {}", id);
        return sysConfigRepository.findById(id);
    }

    /**
     * Delete the sysConfig by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysConfig : {}", id);
        sysConfigRepository.deleteById(id);
    }
}
