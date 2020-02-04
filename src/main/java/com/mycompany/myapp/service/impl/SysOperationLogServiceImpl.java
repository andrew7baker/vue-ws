package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.SysOperationLogService;
import com.mycompany.myapp.domain.SysOperationLog;
import com.mycompany.myapp.repository.SysOperationLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SysOperationLog}.
 */
@Service
@Transactional
public class SysOperationLogServiceImpl implements SysOperationLogService {

    private final Logger log = LoggerFactory.getLogger(SysOperationLogServiceImpl.class);

    private final SysOperationLogRepository sysOperationLogRepository;

    public SysOperationLogServiceImpl(SysOperationLogRepository sysOperationLogRepository) {
        this.sysOperationLogRepository = sysOperationLogRepository;
    }

    /**
     * Save a sysOperationLog.
     *
     * @param sysOperationLog the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SysOperationLog save(SysOperationLog sysOperationLog) {
        log.debug("Request to save SysOperationLog : {}", sysOperationLog);
        return sysOperationLogRepository.save(sysOperationLog);
    }

    /**
     * Get all the sysOperationLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysOperationLog> findAll(Pageable pageable) {
        log.debug("Request to get all SysOperationLogs");
        return sysOperationLogRepository.findAll(pageable);
    }


    /**
     * Get one sysOperationLog by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysOperationLog> findOne(Long id) {
        log.debug("Request to get SysOperationLog : {}", id);
        return sysOperationLogRepository.findById(id);
    }

    /**
     * Delete the sysOperationLog by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysOperationLog : {}", id);
        sysOperationLogRepository.deleteById(id);
    }
}
