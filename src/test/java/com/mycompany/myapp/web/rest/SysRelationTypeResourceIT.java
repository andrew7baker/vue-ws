package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.VuwsmtApp;
import com.mycompany.myapp.domain.SysRelationType;
import com.mycompany.myapp.repository.SysRelationTypeRepository;
import com.mycompany.myapp.service.SysRelationTypeService;
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
 * Integration tests for the {@link SysRelationTypeResource} REST controller.
 */
@SpringBootTest(classes = VuwsmtApp.class)
public class SysRelationTypeResourceIT {

    private static final String DEFAULT_TYPE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TYPED_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TYPED_NAME = "BBBBBBBBBB";

    @Autowired
    private SysRelationTypeRepository sysRelationTypeRepository;

    @Autowired
    private SysRelationTypeService sysRelationTypeService;

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

    private MockMvc restSysRelationTypeMockMvc;

    private SysRelationType sysRelationType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysRelationTypeResource sysRelationTypeResource = new SysRelationTypeResource(sysRelationTypeService);
        this.restSysRelationTypeMockMvc = MockMvcBuilders.standaloneSetup(sysRelationTypeResource)
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
    public static SysRelationType createEntity(EntityManager em) {
        SysRelationType sysRelationType = new SysRelationType()
            .typeCode(DEFAULT_TYPE_CODE)
            .typedName(DEFAULT_TYPED_NAME);
        return sysRelationType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysRelationType createUpdatedEntity(EntityManager em) {
        SysRelationType sysRelationType = new SysRelationType()
            .typeCode(UPDATED_TYPE_CODE)
            .typedName(UPDATED_TYPED_NAME);
        return sysRelationType;
    }

    @BeforeEach
    public void initTest() {
        sysRelationType = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysRelationType() throws Exception {
        int databaseSizeBeforeCreate = sysRelationTypeRepository.findAll().size();

        // Create the SysRelationType
        restSysRelationTypeMockMvc.perform(post("/api/sys-relation-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRelationType)))
            .andExpect(status().isCreated());

        // Validate the SysRelationType in the database
        List<SysRelationType> sysRelationTypeList = sysRelationTypeRepository.findAll();
        assertThat(sysRelationTypeList).hasSize(databaseSizeBeforeCreate + 1);
        SysRelationType testSysRelationType = sysRelationTypeList.get(sysRelationTypeList.size() - 1);
        assertThat(testSysRelationType.getTypeCode()).isEqualTo(DEFAULT_TYPE_CODE);
        assertThat(testSysRelationType.getTypedName()).isEqualTo(DEFAULT_TYPED_NAME);
    }

    @Test
    @Transactional
    public void createSysRelationTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysRelationTypeRepository.findAll().size();

        // Create the SysRelationType with an existing ID
        sysRelationType.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysRelationTypeMockMvc.perform(post("/api/sys-relation-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRelationType)))
            .andExpect(status().isBadRequest());

        // Validate the SysRelationType in the database
        List<SysRelationType> sysRelationTypeList = sysRelationTypeRepository.findAll();
        assertThat(sysRelationTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSysRelationTypes() throws Exception {
        // Initialize the database
        sysRelationTypeRepository.saveAndFlush(sysRelationType);

        // Get all the sysRelationTypeList
        restSysRelationTypeMockMvc.perform(get("/api/sys-relation-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysRelationType.getId().intValue())))
            .andExpect(jsonPath("$.[*].typeCode").value(hasItem(DEFAULT_TYPE_CODE)))
            .andExpect(jsonPath("$.[*].typedName").value(hasItem(DEFAULT_TYPED_NAME)));
    }
    
    @Test
    @Transactional
    public void getSysRelationType() throws Exception {
        // Initialize the database
        sysRelationTypeRepository.saveAndFlush(sysRelationType);

        // Get the sysRelationType
        restSysRelationTypeMockMvc.perform(get("/api/sys-relation-types/{id}", sysRelationType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysRelationType.getId().intValue()))
            .andExpect(jsonPath("$.typeCode").value(DEFAULT_TYPE_CODE))
            .andExpect(jsonPath("$.typedName").value(DEFAULT_TYPED_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingSysRelationType() throws Exception {
        // Get the sysRelationType
        restSysRelationTypeMockMvc.perform(get("/api/sys-relation-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysRelationType() throws Exception {
        // Initialize the database
        sysRelationTypeService.save(sysRelationType);

        int databaseSizeBeforeUpdate = sysRelationTypeRepository.findAll().size();

        // Update the sysRelationType
        SysRelationType updatedSysRelationType = sysRelationTypeRepository.findById(sysRelationType.getId()).get();
        // Disconnect from session so that the updates on updatedSysRelationType are not directly saved in db
        em.detach(updatedSysRelationType);
        updatedSysRelationType
            .typeCode(UPDATED_TYPE_CODE)
            .typedName(UPDATED_TYPED_NAME);

        restSysRelationTypeMockMvc.perform(put("/api/sys-relation-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysRelationType)))
            .andExpect(status().isOk());

        // Validate the SysRelationType in the database
        List<SysRelationType> sysRelationTypeList = sysRelationTypeRepository.findAll();
        assertThat(sysRelationTypeList).hasSize(databaseSizeBeforeUpdate);
        SysRelationType testSysRelationType = sysRelationTypeList.get(sysRelationTypeList.size() - 1);
        assertThat(testSysRelationType.getTypeCode()).isEqualTo(UPDATED_TYPE_CODE);
        assertThat(testSysRelationType.getTypedName()).isEqualTo(UPDATED_TYPED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingSysRelationType() throws Exception {
        int databaseSizeBeforeUpdate = sysRelationTypeRepository.findAll().size();

        // Create the SysRelationType

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysRelationTypeMockMvc.perform(put("/api/sys-relation-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRelationType)))
            .andExpect(status().isBadRequest());

        // Validate the SysRelationType in the database
        List<SysRelationType> sysRelationTypeList = sysRelationTypeRepository.findAll();
        assertThat(sysRelationTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysRelationType() throws Exception {
        // Initialize the database
        sysRelationTypeService.save(sysRelationType);

        int databaseSizeBeforeDelete = sysRelationTypeRepository.findAll().size();

        // Delete the sysRelationType
        restSysRelationTypeMockMvc.perform(delete("/api/sys-relation-types/{id}", sysRelationType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysRelationType> sysRelationTypeList = sysRelationTypeRepository.findAll();
        assertThat(sysRelationTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
