package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.VuwsmtApp;
import com.mycompany.myapp.domain.SysDept;
import com.mycompany.myapp.repository.SysDeptRepository;
import com.mycompany.myapp.service.SysDeptService;
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
 * Integration tests for the {@link SysDeptResource} REST controller.
 */
@SpringBootTest(classes = VuwsmtApp.class)
public class SysDeptResourceIT {

    private static final Long DEFAULT_PARENT_ID = 1L;
    private static final Long UPDATED_PARENT_ID = 2L;

    private static final String DEFAULT_PARENT_IDS = "AAAAAAAAAA";
    private static final String UPDATED_PARENT_IDS = "BBBBBBBBBB";

    private static final String DEFAULT_SIMPLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SIMPLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Integer DEFAULT_SORT = 1;
    private static final Integer UPDATED_SORT = 2;

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_CREATE_USER = 1L;
    private static final Long UPDATED_CREATE_USER = 2L;

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_UPDATE_USER = 1L;
    private static final Long UPDATED_UPDATE_USER = 2L;

    @Autowired
    private SysDeptRepository sysDeptRepository;

    @Autowired
    private SysDeptService sysDeptService;

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

    private MockMvc restSysDeptMockMvc;

    private SysDept sysDept;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysDeptResource sysDeptResource = new SysDeptResource(sysDeptService);
        this.restSysDeptMockMvc = MockMvcBuilders.standaloneSetup(sysDeptResource)
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
    public static SysDept createEntity(EntityManager em) {
        SysDept sysDept = new SysDept()
            .parentId(DEFAULT_PARENT_ID)
            .parentIds(DEFAULT_PARENT_IDS)
            .simpleName(DEFAULT_SIMPLE_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .description(DEFAULT_DESCRIPTION)
            .version(DEFAULT_VERSION)
            .status(DEFAULT_STATUS)
            .sort(DEFAULT_SORT)
            .createTime(DEFAULT_CREATE_TIME)
            .createUser(DEFAULT_CREATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME)
            .updateUser(DEFAULT_UPDATE_USER);
        return sysDept;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysDept createUpdatedEntity(EntityManager em) {
        SysDept sysDept = new SysDept()
            .parentId(UPDATED_PARENT_ID)
            .parentIds(UPDATED_PARENT_IDS)
            .simpleName(UPDATED_SIMPLE_NAME)
            .fullName(UPDATED_FULL_NAME)
            .description(UPDATED_DESCRIPTION)
            .version(UPDATED_VERSION)
            .status(UPDATED_STATUS)
            .sort(UPDATED_SORT)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER);
        return sysDept;
    }

    @BeforeEach
    public void initTest() {
        sysDept = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysDept() throws Exception {
        int databaseSizeBeforeCreate = sysDeptRepository.findAll().size();

        // Create the SysDept
        restSysDeptMockMvc.perform(post("/api/sys-depts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysDept)))
            .andExpect(status().isCreated());

        // Validate the SysDept in the database
        List<SysDept> sysDeptList = sysDeptRepository.findAll();
        assertThat(sysDeptList).hasSize(databaseSizeBeforeCreate + 1);
        SysDept testSysDept = sysDeptList.get(sysDeptList.size() - 1);
        assertThat(testSysDept.getParentId()).isEqualTo(DEFAULT_PARENT_ID);
        assertThat(testSysDept.getParentIds()).isEqualTo(DEFAULT_PARENT_IDS);
        assertThat(testSysDept.getSimpleName()).isEqualTo(DEFAULT_SIMPLE_NAME);
        assertThat(testSysDept.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testSysDept.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testSysDept.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testSysDept.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSysDept.getSort()).isEqualTo(DEFAULT_SORT);
        assertThat(testSysDept.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysDept.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testSysDept.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testSysDept.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
    }

    @Test
    @Transactional
    public void createSysDeptWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysDeptRepository.findAll().size();

        // Create the SysDept with an existing ID
        sysDept.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysDeptMockMvc.perform(post("/api/sys-depts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysDept)))
            .andExpect(status().isBadRequest());

        // Validate the SysDept in the database
        List<SysDept> sysDeptList = sysDeptRepository.findAll();
        assertThat(sysDeptList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSysDepts() throws Exception {
        // Initialize the database
        sysDeptRepository.saveAndFlush(sysDept);

        // Get all the sysDeptList
        restSysDeptMockMvc.perform(get("/api/sys-depts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysDept.getId().intValue())))
            .andExpect(jsonPath("$.[*].parentId").value(hasItem(DEFAULT_PARENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].parentIds").value(hasItem(DEFAULT_PARENT_IDS)))
            .andExpect(jsonPath("$.[*].simpleName").value(hasItem(DEFAULT_SIMPLE_NAME)))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].sort").value(hasItem(DEFAULT_SORT)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER.intValue())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER.intValue())));
    }
    
    @Test
    @Transactional
    public void getSysDept() throws Exception {
        // Initialize the database
        sysDeptRepository.saveAndFlush(sysDept);

        // Get the sysDept
        restSysDeptMockMvc.perform(get("/api/sys-depts/{id}", sysDept.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysDept.getId().intValue()))
            .andExpect(jsonPath("$.parentId").value(DEFAULT_PARENT_ID.intValue()))
            .andExpect(jsonPath("$.parentIds").value(DEFAULT_PARENT_IDS))
            .andExpect(jsonPath("$.simpleName").value(DEFAULT_SIMPLE_NAME))
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.sort").value(DEFAULT_SORT))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER.intValue()))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSysDept() throws Exception {
        // Get the sysDept
        restSysDeptMockMvc.perform(get("/api/sys-depts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysDept() throws Exception {
        // Initialize the database
        sysDeptService.save(sysDept);

        int databaseSizeBeforeUpdate = sysDeptRepository.findAll().size();

        // Update the sysDept
        SysDept updatedSysDept = sysDeptRepository.findById(sysDept.getId()).get();
        // Disconnect from session so that the updates on updatedSysDept are not directly saved in db
        em.detach(updatedSysDept);
        updatedSysDept
            .parentId(UPDATED_PARENT_ID)
            .parentIds(UPDATED_PARENT_IDS)
            .simpleName(UPDATED_SIMPLE_NAME)
            .fullName(UPDATED_FULL_NAME)
            .description(UPDATED_DESCRIPTION)
            .version(UPDATED_VERSION)
            .status(UPDATED_STATUS)
            .sort(UPDATED_SORT)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER);

        restSysDeptMockMvc.perform(put("/api/sys-depts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysDept)))
            .andExpect(status().isOk());

        // Validate the SysDept in the database
        List<SysDept> sysDeptList = sysDeptRepository.findAll();
        assertThat(sysDeptList).hasSize(databaseSizeBeforeUpdate);
        SysDept testSysDept = sysDeptList.get(sysDeptList.size() - 1);
        assertThat(testSysDept.getParentId()).isEqualTo(UPDATED_PARENT_ID);
        assertThat(testSysDept.getParentIds()).isEqualTo(UPDATED_PARENT_IDS);
        assertThat(testSysDept.getSimpleName()).isEqualTo(UPDATED_SIMPLE_NAME);
        assertThat(testSysDept.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testSysDept.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testSysDept.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testSysDept.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSysDept.getSort()).isEqualTo(UPDATED_SORT);
        assertThat(testSysDept.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysDept.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testSysDept.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysDept.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
    }

    @Test
    @Transactional
    public void updateNonExistingSysDept() throws Exception {
        int databaseSizeBeforeUpdate = sysDeptRepository.findAll().size();

        // Create the SysDept

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysDeptMockMvc.perform(put("/api/sys-depts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysDept)))
            .andExpect(status().isBadRequest());

        // Validate the SysDept in the database
        List<SysDept> sysDeptList = sysDeptRepository.findAll();
        assertThat(sysDeptList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysDept() throws Exception {
        // Initialize the database
        sysDeptService.save(sysDept);

        int databaseSizeBeforeDelete = sysDeptRepository.findAll().size();

        // Delete the sysDept
        restSysDeptMockMvc.perform(delete("/api/sys-depts/{id}", sysDept.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysDept> sysDeptList = sysDeptRepository.findAll();
        assertThat(sysDeptList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
