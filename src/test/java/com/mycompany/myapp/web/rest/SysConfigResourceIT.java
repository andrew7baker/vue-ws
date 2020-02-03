package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.VuwsmtApp;
import com.mycompany.myapp.domain.SysConfig;
import com.mycompany.myapp.repository.SysConfigRepository;
import com.mycompany.myapp.service.SysConfigService;
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
 * Integration tests for the {@link SysConfigResource} REST controller.
 */
@SpringBootTest(classes = VuwsmtApp.class)
public class SysConfigResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DICT_FLAG = "AAAAAAAAAA";
    private static final String UPDATED_DICT_FLAG = "BBBBBBBBBB";

    private static final Long DEFAULT_DICT_TYPE_ID = 1L;
    private static final Long UPDATED_DICT_TYPE_ID = 2L;

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_CREATE_USER = 1L;
    private static final Long UPDATED_CREATE_USER = 2L;

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_UPDATE_USER = 1L;
    private static final Long UPDATED_UPDATE_USER = 2L;

    @Autowired
    private SysConfigRepository sysConfigRepository;

    @Autowired
    private SysConfigService sysConfigService;

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

    private MockMvc restSysConfigMockMvc;

    private SysConfig sysConfig;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysConfigResource sysConfigResource = new SysConfigResource(sysConfigService);
        this.restSysConfigMockMvc = MockMvcBuilders.standaloneSetup(sysConfigResource)
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
    public static SysConfig createEntity(EntityManager em) {
        SysConfig sysConfig = new SysConfig()
            .name(DEFAULT_NAME)
            .code(DEFAULT_CODE)
            .dictFlag(DEFAULT_DICT_FLAG)
            .dictTypeId(DEFAULT_DICT_TYPE_ID)
            .value(DEFAULT_VALUE)
            .remark(DEFAULT_REMARK)
            .createTime(DEFAULT_CREATE_TIME)
            .createUser(DEFAULT_CREATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME)
            .updateUser(DEFAULT_UPDATE_USER);
        return sysConfig;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysConfig createUpdatedEntity(EntityManager em) {
        SysConfig sysConfig = new SysConfig()
            .name(UPDATED_NAME)
            .code(UPDATED_CODE)
            .dictFlag(UPDATED_DICT_FLAG)
            .dictTypeId(UPDATED_DICT_TYPE_ID)
            .value(UPDATED_VALUE)
            .remark(UPDATED_REMARK)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER);
        return sysConfig;
    }

    @BeforeEach
    public void initTest() {
        sysConfig = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysConfig() throws Exception {
        int databaseSizeBeforeCreate = sysConfigRepository.findAll().size();

        // Create the SysConfig
        restSysConfigMockMvc.perform(post("/api/sys-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysConfig)))
            .andExpect(status().isCreated());

        // Validate the SysConfig in the database
        List<SysConfig> sysConfigList = sysConfigRepository.findAll();
        assertThat(sysConfigList).hasSize(databaseSizeBeforeCreate + 1);
        SysConfig testSysConfig = sysConfigList.get(sysConfigList.size() - 1);
        assertThat(testSysConfig.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSysConfig.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testSysConfig.getDictFlag()).isEqualTo(DEFAULT_DICT_FLAG);
        assertThat(testSysConfig.getDictTypeId()).isEqualTo(DEFAULT_DICT_TYPE_ID);
        assertThat(testSysConfig.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testSysConfig.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testSysConfig.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysConfig.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testSysConfig.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testSysConfig.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
    }

    @Test
    @Transactional
    public void createSysConfigWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysConfigRepository.findAll().size();

        // Create the SysConfig with an existing ID
        sysConfig.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysConfigMockMvc.perform(post("/api/sys-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysConfig)))
            .andExpect(status().isBadRequest());

        // Validate the SysConfig in the database
        List<SysConfig> sysConfigList = sysConfigRepository.findAll();
        assertThat(sysConfigList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSysConfigs() throws Exception {
        // Initialize the database
        sysConfigRepository.saveAndFlush(sysConfig);

        // Get all the sysConfigList
        restSysConfigMockMvc.perform(get("/api/sys-configs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysConfig.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].dictFlag").value(hasItem(DEFAULT_DICT_FLAG)))
            .andExpect(jsonPath("$.[*].dictTypeId").value(hasItem(DEFAULT_DICT_TYPE_ID.intValue())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER.intValue())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER.intValue())));
    }
    
    @Test
    @Transactional
    public void getSysConfig() throws Exception {
        // Initialize the database
        sysConfigRepository.saveAndFlush(sysConfig);

        // Get the sysConfig
        restSysConfigMockMvc.perform(get("/api/sys-configs/{id}", sysConfig.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysConfig.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.dictFlag").value(DEFAULT_DICT_FLAG))
            .andExpect(jsonPath("$.dictTypeId").value(DEFAULT_DICT_TYPE_ID.intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER.intValue()))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSysConfig() throws Exception {
        // Get the sysConfig
        restSysConfigMockMvc.perform(get("/api/sys-configs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysConfig() throws Exception {
        // Initialize the database
        sysConfigService.save(sysConfig);

        int databaseSizeBeforeUpdate = sysConfigRepository.findAll().size();

        // Update the sysConfig
        SysConfig updatedSysConfig = sysConfigRepository.findById(sysConfig.getId()).get();
        // Disconnect from session so that the updates on updatedSysConfig are not directly saved in db
        em.detach(updatedSysConfig);
        updatedSysConfig
            .name(UPDATED_NAME)
            .code(UPDATED_CODE)
            .dictFlag(UPDATED_DICT_FLAG)
            .dictTypeId(UPDATED_DICT_TYPE_ID)
            .value(UPDATED_VALUE)
            .remark(UPDATED_REMARK)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER);

        restSysConfigMockMvc.perform(put("/api/sys-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysConfig)))
            .andExpect(status().isOk());

        // Validate the SysConfig in the database
        List<SysConfig> sysConfigList = sysConfigRepository.findAll();
        assertThat(sysConfigList).hasSize(databaseSizeBeforeUpdate);
        SysConfig testSysConfig = sysConfigList.get(sysConfigList.size() - 1);
        assertThat(testSysConfig.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSysConfig.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testSysConfig.getDictFlag()).isEqualTo(UPDATED_DICT_FLAG);
        assertThat(testSysConfig.getDictTypeId()).isEqualTo(UPDATED_DICT_TYPE_ID);
        assertThat(testSysConfig.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testSysConfig.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testSysConfig.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysConfig.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testSysConfig.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysConfig.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
    }

    @Test
    @Transactional
    public void updateNonExistingSysConfig() throws Exception {
        int databaseSizeBeforeUpdate = sysConfigRepository.findAll().size();

        // Create the SysConfig

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysConfigMockMvc.perform(put("/api/sys-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysConfig)))
            .andExpect(status().isBadRequest());

        // Validate the SysConfig in the database
        List<SysConfig> sysConfigList = sysConfigRepository.findAll();
        assertThat(sysConfigList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysConfig() throws Exception {
        // Initialize the database
        sysConfigService.save(sysConfig);

        int databaseSizeBeforeDelete = sysConfigRepository.findAll().size();

        // Delete the sysConfig
        restSysConfigMockMvc.perform(delete("/api/sys-configs/{id}", sysConfig.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysConfig> sysConfigList = sysConfigRepository.findAll();
        assertThat(sysConfigList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
