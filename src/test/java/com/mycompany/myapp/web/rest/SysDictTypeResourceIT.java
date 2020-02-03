package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.VuwsmtApp;
import com.mycompany.myapp.domain.SysDictType;
import com.mycompany.myapp.repository.SysDictTypeRepository;
import com.mycompany.myapp.service.SysDictTypeService;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SysDictTypeResource} REST controller.
 */
@SpringBootTest(classes = VuwsmtApp.class)
public class SysDictTypeResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SYSTEM_FLAG = "AAAAAAAAAA";
    private static final String UPDATED_SYSTEM_FLAG = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Integer DEFAULT_SORT = 1;
    private static final Integer UPDATED_SORT = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_CREATE_USER = 1L;
    private static final Long UPDATED_CREATE_USER = 2L;

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_UPDATE_USER = 1L;
    private static final Long UPDATED_UPDATE_USER = 2L;

    @Autowired
    private SysDictTypeRepository sysDictTypeRepository;

    @Autowired
    private SysDictTypeService sysDictTypeService;

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

    private MockMvc restSysDictTypeMockMvc;

    private SysDictType sysDictType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysDictTypeResource sysDictTypeResource = new SysDictTypeResource(sysDictTypeService);
        this.restSysDictTypeMockMvc = MockMvcBuilders.standaloneSetup(sysDictTypeResource)
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
    public static SysDictType createEntity(EntityManager em) {
        SysDictType sysDictType = new SysDictType()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .systemFlag(DEFAULT_SYSTEM_FLAG)
            .status(DEFAULT_STATUS)
            .sort(DEFAULT_SORT)
            .description(DEFAULT_DESCRIPTION)
            .createTime(DEFAULT_CREATE_TIME)
            .createUser(DEFAULT_CREATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME)
            .updateUser(DEFAULT_UPDATE_USER);
        return sysDictType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysDictType createUpdatedEntity(EntityManager em) {
        SysDictType sysDictType = new SysDictType()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .systemFlag(UPDATED_SYSTEM_FLAG)
            .status(UPDATED_STATUS)
            .sort(UPDATED_SORT)
            .description(UPDATED_DESCRIPTION)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER);
        return sysDictType;
    }

    @BeforeEach
    public void initTest() {
        sysDictType = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysDictType() throws Exception {
        int databaseSizeBeforeCreate = sysDictTypeRepository.findAll().size();

        // Create the SysDictType
        restSysDictTypeMockMvc.perform(post("/api/sys-dict-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysDictType)))
            .andExpect(status().isCreated());

        // Validate the SysDictType in the database
        List<SysDictType> sysDictTypeList = sysDictTypeRepository.findAll();
        assertThat(sysDictTypeList).hasSize(databaseSizeBeforeCreate + 1);
        SysDictType testSysDictType = sysDictTypeList.get(sysDictTypeList.size() - 1);
        assertThat(testSysDictType.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testSysDictType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSysDictType.getSystemFlag()).isEqualTo(DEFAULT_SYSTEM_FLAG);
        assertThat(testSysDictType.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSysDictType.getSort()).isEqualTo(DEFAULT_SORT);
        assertThat(testSysDictType.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testSysDictType.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysDictType.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testSysDictType.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testSysDictType.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
    }

    @Test
    @Transactional
    public void createSysDictTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysDictTypeRepository.findAll().size();

        // Create the SysDictType with an existing ID
        sysDictType.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysDictTypeMockMvc.perform(post("/api/sys-dict-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysDictType)))
            .andExpect(status().isBadRequest());

        // Validate the SysDictType in the database
        List<SysDictType> sysDictTypeList = sysDictTypeRepository.findAll();
        assertThat(sysDictTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSysDictTypes() throws Exception {
        // Initialize the database
        sysDictTypeRepository.saveAndFlush(sysDictType);

        // Get all the sysDictTypeList
        restSysDictTypeMockMvc.perform(get("/api/sys-dict-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysDictType.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].systemFlag").value(hasItem(DEFAULT_SYSTEM_FLAG)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].sort").value(hasItem(DEFAULT_SORT)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER.intValue())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER.intValue())));
    }
    
    @Test
    @Transactional
    public void getSysDictType() throws Exception {
        // Initialize the database
        sysDictTypeRepository.saveAndFlush(sysDictType);

        // Get the sysDictType
        restSysDictTypeMockMvc.perform(get("/api/sys-dict-types/{id}", sysDictType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysDictType.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.systemFlag").value(DEFAULT_SYSTEM_FLAG))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.sort").value(DEFAULT_SORT))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER.intValue()))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSysDictType() throws Exception {
        // Get the sysDictType
        restSysDictTypeMockMvc.perform(get("/api/sys-dict-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysDictType() throws Exception {
        // Initialize the database
        sysDictTypeService.save(sysDictType);

        int databaseSizeBeforeUpdate = sysDictTypeRepository.findAll().size();

        // Update the sysDictType
        SysDictType updatedSysDictType = sysDictTypeRepository.findById(sysDictType.getId()).get();
        // Disconnect from session so that the updates on updatedSysDictType are not directly saved in db
        em.detach(updatedSysDictType);
        updatedSysDictType
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .systemFlag(UPDATED_SYSTEM_FLAG)
            .status(UPDATED_STATUS)
            .sort(UPDATED_SORT)
            .description(UPDATED_DESCRIPTION)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER);

        restSysDictTypeMockMvc.perform(put("/api/sys-dict-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysDictType)))
            .andExpect(status().isOk());

        // Validate the SysDictType in the database
        List<SysDictType> sysDictTypeList = sysDictTypeRepository.findAll();
        assertThat(sysDictTypeList).hasSize(databaseSizeBeforeUpdate);
        SysDictType testSysDictType = sysDictTypeList.get(sysDictTypeList.size() - 1);
        assertThat(testSysDictType.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testSysDictType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSysDictType.getSystemFlag()).isEqualTo(UPDATED_SYSTEM_FLAG);
        assertThat(testSysDictType.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSysDictType.getSort()).isEqualTo(UPDATED_SORT);
        assertThat(testSysDictType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testSysDictType.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysDictType.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testSysDictType.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysDictType.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
    }

    @Test
    @Transactional
    public void updateNonExistingSysDictType() throws Exception {
        int databaseSizeBeforeUpdate = sysDictTypeRepository.findAll().size();

        // Create the SysDictType

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysDictTypeMockMvc.perform(put("/api/sys-dict-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysDictType)))
            .andExpect(status().isBadRequest());

        // Validate the SysDictType in the database
        List<SysDictType> sysDictTypeList = sysDictTypeRepository.findAll();
        assertThat(sysDictTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysDictType() throws Exception {
        // Initialize the database
        sysDictTypeService.save(sysDictType);

        int databaseSizeBeforeDelete = sysDictTypeRepository.findAll().size();

        // Delete the sysDictType
        restSysDictTypeMockMvc.perform(delete("/api/sys-dict-types/{id}", sysDictType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysDictType> sysDictTypeList = sysDictTypeRepository.findAll();
        assertThat(sysDictTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
