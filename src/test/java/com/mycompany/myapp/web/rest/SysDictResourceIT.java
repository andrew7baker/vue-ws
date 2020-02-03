package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.VuwsmtApp;
import com.mycompany.myapp.domain.SysDict;
import com.mycompany.myapp.repository.SysDictRepository;
import com.mycompany.myapp.service.SysDictService;
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
 * Integration tests for the {@link SysDictResource} REST controller.
 */
@SpringBootTest(classes = VuwsmtApp.class)
public class SysDictResourceIT {

    private static final Long DEFAULT_DIC_TYPE_ID = 1L;
    private static final Long UPDATED_DIC_TYPE_ID = 2L;

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_PARENT_ID = 1L;
    private static final Long UPDATED_PARENT_ID = 2L;

    private static final String DEFAULT_PARENT_IDS = "AAAAAAAAAA";
    private static final String UPDATED_PARENT_IDS = "BBBBBBBBBB";

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
    private SysDictRepository sysDictRepository;

    @Autowired
    private SysDictService sysDictService;

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

    private MockMvc restSysDictMockMvc;

    private SysDict sysDict;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysDictResource sysDictResource = new SysDictResource(sysDictService);
        this.restSysDictMockMvc = MockMvcBuilders.standaloneSetup(sysDictResource)
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
    public static SysDict createEntity(EntityManager em) {
        SysDict sysDict = new SysDict()
            .dicTypeId(DEFAULT_DIC_TYPE_ID)
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .parentId(DEFAULT_PARENT_ID)
            .parentIds(DEFAULT_PARENT_IDS)
            .status(DEFAULT_STATUS)
            .sort(DEFAULT_SORT)
            .description(DEFAULT_DESCRIPTION)
            .createTime(DEFAULT_CREATE_TIME)
            .createUser(DEFAULT_CREATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME)
            .updateUser(DEFAULT_UPDATE_USER);
        return sysDict;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysDict createUpdatedEntity(EntityManager em) {
        SysDict sysDict = new SysDict()
            .dicTypeId(UPDATED_DIC_TYPE_ID)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .parentId(UPDATED_PARENT_ID)
            .parentIds(UPDATED_PARENT_IDS)
            .status(UPDATED_STATUS)
            .sort(UPDATED_SORT)
            .description(UPDATED_DESCRIPTION)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER);
        return sysDict;
    }

    @BeforeEach
    public void initTest() {
        sysDict = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysDict() throws Exception {
        int databaseSizeBeforeCreate = sysDictRepository.findAll().size();

        // Create the SysDict
        restSysDictMockMvc.perform(post("/api/sys-dicts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysDict)))
            .andExpect(status().isCreated());

        // Validate the SysDict in the database
        List<SysDict> sysDictList = sysDictRepository.findAll();
        assertThat(sysDictList).hasSize(databaseSizeBeforeCreate + 1);
        SysDict testSysDict = sysDictList.get(sysDictList.size() - 1);
        assertThat(testSysDict.getDicTypeId()).isEqualTo(DEFAULT_DIC_TYPE_ID);
        assertThat(testSysDict.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testSysDict.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSysDict.getParentId()).isEqualTo(DEFAULT_PARENT_ID);
        assertThat(testSysDict.getParentIds()).isEqualTo(DEFAULT_PARENT_IDS);
        assertThat(testSysDict.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSysDict.getSort()).isEqualTo(DEFAULT_SORT);
        assertThat(testSysDict.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testSysDict.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysDict.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testSysDict.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testSysDict.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
    }

    @Test
    @Transactional
    public void createSysDictWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysDictRepository.findAll().size();

        // Create the SysDict with an existing ID
        sysDict.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysDictMockMvc.perform(post("/api/sys-dicts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysDict)))
            .andExpect(status().isBadRequest());

        // Validate the SysDict in the database
        List<SysDict> sysDictList = sysDictRepository.findAll();
        assertThat(sysDictList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSysDicts() throws Exception {
        // Initialize the database
        sysDictRepository.saveAndFlush(sysDict);

        // Get all the sysDictList
        restSysDictMockMvc.perform(get("/api/sys-dicts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysDict.getId().intValue())))
            .andExpect(jsonPath("$.[*].dicTypeId").value(hasItem(DEFAULT_DIC_TYPE_ID.intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].parentId").value(hasItem(DEFAULT_PARENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].parentIds").value(hasItem(DEFAULT_PARENT_IDS)))
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
    public void getSysDict() throws Exception {
        // Initialize the database
        sysDictRepository.saveAndFlush(sysDict);

        // Get the sysDict
        restSysDictMockMvc.perform(get("/api/sys-dicts/{id}", sysDict.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysDict.getId().intValue()))
            .andExpect(jsonPath("$.dicTypeId").value(DEFAULT_DIC_TYPE_ID.intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.parentId").value(DEFAULT_PARENT_ID.intValue()))
            .andExpect(jsonPath("$.parentIds").value(DEFAULT_PARENT_IDS))
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
    public void getNonExistingSysDict() throws Exception {
        // Get the sysDict
        restSysDictMockMvc.perform(get("/api/sys-dicts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysDict() throws Exception {
        // Initialize the database
        sysDictService.save(sysDict);

        int databaseSizeBeforeUpdate = sysDictRepository.findAll().size();

        // Update the sysDict
        SysDict updatedSysDict = sysDictRepository.findById(sysDict.getId()).get();
        // Disconnect from session so that the updates on updatedSysDict are not directly saved in db
        em.detach(updatedSysDict);
        updatedSysDict
            .dicTypeId(UPDATED_DIC_TYPE_ID)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .parentId(UPDATED_PARENT_ID)
            .parentIds(UPDATED_PARENT_IDS)
            .status(UPDATED_STATUS)
            .sort(UPDATED_SORT)
            .description(UPDATED_DESCRIPTION)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER);

        restSysDictMockMvc.perform(put("/api/sys-dicts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysDict)))
            .andExpect(status().isOk());

        // Validate the SysDict in the database
        List<SysDict> sysDictList = sysDictRepository.findAll();
        assertThat(sysDictList).hasSize(databaseSizeBeforeUpdate);
        SysDict testSysDict = sysDictList.get(sysDictList.size() - 1);
        assertThat(testSysDict.getDicTypeId()).isEqualTo(UPDATED_DIC_TYPE_ID);
        assertThat(testSysDict.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testSysDict.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSysDict.getParentId()).isEqualTo(UPDATED_PARENT_ID);
        assertThat(testSysDict.getParentIds()).isEqualTo(UPDATED_PARENT_IDS);
        assertThat(testSysDict.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSysDict.getSort()).isEqualTo(UPDATED_SORT);
        assertThat(testSysDict.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testSysDict.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysDict.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testSysDict.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysDict.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
    }

    @Test
    @Transactional
    public void updateNonExistingSysDict() throws Exception {
        int databaseSizeBeforeUpdate = sysDictRepository.findAll().size();

        // Create the SysDict

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysDictMockMvc.perform(put("/api/sys-dicts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysDict)))
            .andExpect(status().isBadRequest());

        // Validate the SysDict in the database
        List<SysDict> sysDictList = sysDictRepository.findAll();
        assertThat(sysDictList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysDict() throws Exception {
        // Initialize the database
        sysDictService.save(sysDict);

        int databaseSizeBeforeDelete = sysDictRepository.findAll().size();

        // Delete the sysDict
        restSysDictMockMvc.perform(delete("/api/sys-dicts/{id}", sysDict.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysDict> sysDictList = sysDictRepository.findAll();
        assertThat(sysDictList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
