package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.SysFileInfoService;
import com.mycompany.myapp.domain.SysFileInfo;
import com.mycompany.myapp.repository.SysFileInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SysFileInfo}.
 */
@Service
@Transactional
public class SysFileInfoServiceImpl implements SysFileInfoService {

    private final Logger log = LoggerFactory.getLogger(SysFileInfoServiceImpl.class);

    private final SysFileInfoRepository sysFileInfoRepository;

    public SysFileInfoServiceImpl(SysFileInfoRepository sysFileInfoRepository) {
        this.sysFileInfoRepository = sysFileInfoRepository;
    }

    /**
     * Save a sysFileInfo.
     *
     * @param sysFileInfo the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SysFileInfo save(SysFileInfo sysFileInfo) {
        log.debug("Request to save SysFileInfo : {}", sysFileInfo);
        return sysFileInfoRepository.save(sysFileInfo);
    }

    /**
     * Get all the sysFileInfos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysFileInfo> findAll(Pageable pageable) {
        log.debug("Request to get all SysFileInfos");
        return sysFileInfoRepository.findAll(pageable);
    }


    /**
     * Get one sysFileInfo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysFileInfo> findOne(Long id) {
        log.debug("Request to get SysFileInfo : {}", id);
        return sysFileInfoRepository.findById(id);
    }

    /**
     * Delete the sysFileInfo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysFileInfo : {}", id);
        sysFileInfoRepository.deleteById(id);
    }
}
