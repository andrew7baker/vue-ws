package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ProductionService;
import com.mycompany.myapp.domain.Production;
import com.mycompany.myapp.repository.ProductionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Production}.
 */
@Service
@Transactional
public class ProductionServiceImpl implements ProductionService {

    private final Logger log = LoggerFactory.getLogger(ProductionServiceImpl.class);

    private final ProductionRepository productionRepository;

    public ProductionServiceImpl(ProductionRepository productionRepository) {
        this.productionRepository = productionRepository;
    }

    /**
     * Save a production.
     *
     * @param production the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Production save(Production production) {
        log.debug("Request to save Production : {}", production);
        return productionRepository.save(production);
    }

    /**
     * Get all the productions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Production> findAll(Pageable pageable) {
        log.debug("Request to get all Productions");
        return productionRepository.findAll(pageable);
    }


    /**
     * Get one production by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Production> findOne(Long id) {
        log.debug("Request to get Production : {}", id);
        return productionRepository.findById(id);
    }

    /**
     * Delete the production by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Production : {}", id);
        productionRepository.deleteById(id);
    }
}
