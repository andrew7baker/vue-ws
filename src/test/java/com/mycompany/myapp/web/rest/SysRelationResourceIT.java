package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.VuwsmtApp;
import com.mycompany.myapp.domain.SysRelation;
import com.mycompany.myapp.repository.SysRelationRepository;
import com.mycompany.myapp.service.SysRelationService;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SysRelationResource} REST controller.
 */
@SpringBootTest(classes = VuwsmtApp.class)
public class SysRelationResourceIT {

    private static final Long DEFAULT_FROM_ID = 1L;
    private static final Long UPDATED_FROM_ID = 2L;

    private static final Long DEFAULT_TO_ID = 1L;
    private static final Long UPDATED_TO_ID = 2L;

    private static final String DEFAULT_TYPE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_CODE = "BBBBBBBBBB";

    @Autowired
    private SysRelationRepository sysRelationRepository;

    @Autowired
    private SysRelationService sysRelationService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restSysRelationMockMvc;

    private SysRelation sysRelation;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysRelationResource sysRelationResource = new SysRelationResource(sysRelationService);
        this.restSysRelationMockMvc = MockMvcBuilders.standaloneSetup(sysRelationResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysRelation createEntity(EntityManager em) {
        SysRelation sysRelation = new SysRelation()
            .fromId(DEFAULT_FROM_ID)
            .toId(DEFAULT_TO_ID)
            .typeCode(DEFAULT_TYPE_CODE);
        return sysRelation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysRelation createUpdatedEntity(EntityManager em) {
        SysRelation sysRelation = new SysRelation()
            .fromId(UPDATED_FROM_ID)
            .toId(UPDATED_TO_ID)
            .typeCode(UPDATED_TYPE_CODE);
        return sysRelation;
    }

    @BeforeEach
    public void initTest() {
        sysRelation = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysRelation() throws Exception {
        int databaseSizeBeforeCreate = sysRelationRepository.findAll().size();

        // Create the SysRelation
        restSysRelationMockMvc.perform(post("/api/sys-relations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRelation)))
            .andExpect(status().isCreated());

        // Validate the SysRelation in the database
        List<SysRelation> sysRelationList = sysRelationRepository.findAll();
        assertThat(sysRelationList).hasSize(databaseSizeBeforeCreate + 1);
        SysRelation testSysRelation = sysRelationList.get(sysRelationList.size() - 1);
        assertThat(testSysRelation.getFromId()).isEqualTo(DEFAULT_FROM_ID);
        assertThat(testSysRelation.getToId()).isEqualTo(DEFAULT_TO_ID);
        assertThat(testSysRelation.getTypeCode()).isEqualTo(DEFAULT_TYPE_CODE);
    }

    @Test
    @Transactional
    public void createSysRelationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysRelationRepository.findAll().size();

        // Create the SysRelation with an existing ID
        sysRelation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysRelationMockMvc.perform(post("/api/sys-relations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRelation)))
            .andExpect(status().isBadRequest());

        // Validate the SysRelation in the database
        List<SysRelation> sysRelationList = sysRelationRepository.findAll();
        assertThat(sysRelationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSysRelations() throws Exception {
        // Initialize the database
        sysRelationRepository.saveAndFlush(sysRelation);

        // Get all the sysRelationList
        restSysRelationMockMvc.perform(get("/api/sys-relations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysRelation.getId().intValue())))
            .andExpect(jsonPath("$.[*].fromId").value(hasItem(DEFAULT_FROM_ID.intValue())))
            .andExpect(jsonPath("$.[*].toId").value(hasItem(DEFAULT_TO_ID.intValue())))
            .andExpect(jsonPath("$.[*].typeCode").value(hasItem(DEFAULT_TYPE_CODE)));
    }
    
    @Test
    @Transactional
    public void getSysRelation() throws Exception {
        // Initialize the database
        sysRelationRepository.saveAndFlush(sysRelation);

        // Get the sysRelation
        restSysRelationMockMvc.perform(get("/api/sys-relations/{id}", sysRelation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysRelation.getId().intValue()))
            .andExpect(jsonPath("$.fromId").value(DEFAULT_FROM_ID.intValue()))
            .andExpect(jsonPath("$.toId").value(DEFAULT_TO_ID.intValue()))
            .andExpect(jsonPath("$.typeCode").value(DEFAULT_TYPE_CODE));
    }

    @Test
    @Transactional
    public void getNonExistingSysRelation() throws Exception {
        // Get the sysRelation
        restSysRelationMockMvc.perform(get("/api/sys-relations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysRelation() throws Exception {
        // Initialize the database
        sysRelationService.save(sysRelation);

        int databaseSizeBeforeUpdate = sysRelationRepository.findAll().size();

        // Update the sysRelation
        SysRelation updatedSysRelation = sysRelationRepository.findById(sysRelation.getId()).get();
        // Disconnect from session so that the updates on updatedSysRelation are not directly saved in db
        em.detach(updatedSysRelation);
        updatedSysRelation
            .fromId(UPDATED_FROM_ID)
            .toId(UPDATED_TO_ID)
            .typeCode(UPDATED_TYPE_CODE);

        restSysRelationMockMvc.perform(put("/api/sys-relations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysRelation)))
            .andExpect(status().isOk());

        // Validate the SysRelation in the database
        List<SysRelation> sysRelationList = sysRelationRepository.findAll();
        assertThat(sysRelationList).hasSize(databaseSizeBeforeUpdate);
        SysRelation testSysRelation = sysRelationList.get(sysRelationList.size() - 1);
        assertThat(testSysRelation.getFromId()).isEqualTo(UPDATED_FROM_ID);
        assertThat(testSysRelation.getToId()).isEqualTo(UPDATED_TO_ID);
        assertThat(testSysRelation.getTypeCode()).isEqualTo(UPDATED_TYPE_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingSysRelation() throws Exception {
        int databaseSizeBeforeUpdate = sysRelationRepository.findAll().size();

        // Create the SysRelation

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysRelationMockMvc.perform(put("/api/sys-relations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRelation)))
            .andExpect(status().isBadRequest());

        // Validate the SysRelation in the database
        List<SysRelation> sysRelationList = sysRelationRepository.findAll();
        assertThat(sysRelationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysRelation() throws Exception {
        // Initialize the database
        sysRelationService.save(sysRelation);

        int databaseSizeBeforeDelete = sysRelationRepository.findAll().size();

        // Delete the sysRelation
        restSysRelationMockMvc.perform(delete("/api/sys-relations/{id}", sysRelation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysRelation> sysRelationList = sysRelationRepository.findAll();
        assertThat(sysRelationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
