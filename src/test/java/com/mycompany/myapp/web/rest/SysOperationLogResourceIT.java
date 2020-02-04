package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.VuwsmtApp;
import com.mycompany.myapp.domain.SysOperationLog;
import com.mycompany.myapp.repository.SysOperationLogRepository;
import com.mycompany.myapp.service.SysOperationLogService;
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
 * Integration tests for the {@link SysOperationLogResource} REST controller.
 */
@SpringBootTest(classes = VuwsmtApp.class)
public class SysOperationLogResourceIT {

    private static final String DEFAULT_LOG_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_LOG_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_LOG_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LOG_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    private static final String DEFAULT_CLASS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CLASS_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_METHOD = "AAAAAAAAAA";
    private static final String UPDATED_METHOD = "BBBBBBBBBB";

    private static final String DEFAULT_SUCCEED = "AAAAAAAAAA";
    private static final String UPDATED_SUCCEED = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    @Autowired
    private SysOperationLogRepository sysOperationLogRepository;

    @Autowired
    private SysOperationLogService sysOperationLogService;

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

    private MockMvc restSysOperationLogMockMvc;

    private SysOperationLog sysOperationLog;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysOperationLogResource sysOperationLogResource = new SysOperationLogResource(sysOperationLogService);
        this.restSysOperationLogMockMvc = MockMvcBuilders.standaloneSetup(sysOperationLogResource)
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
    public static SysOperationLog createEntity(EntityManager em) {
        SysOperationLog sysOperationLog = new SysOperationLog()
            .logType(DEFAULT_LOG_TYPE)
            .logName(DEFAULT_LOG_NAME)
            .userId(DEFAULT_USER_ID)
            .className(DEFAULT_CLASS_NAME)
            .method(DEFAULT_METHOD)
            .succeed(DEFAULT_SUCCEED)
            .createTime(DEFAULT_CREATE_TIME)
            .message(DEFAULT_MESSAGE);
        return sysOperationLog;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysOperationLog createUpdatedEntity(EntityManager em) {
        SysOperationLog sysOperationLog = new SysOperationLog()
            .logType(UPDATED_LOG_TYPE)
            .logName(UPDATED_LOG_NAME)
            .userId(UPDATED_USER_ID)
            .className(UPDATED_CLASS_NAME)
            .method(UPDATED_METHOD)
            .succeed(UPDATED_SUCCEED)
            .createTime(UPDATED_CREATE_TIME)
            .message(UPDATED_MESSAGE);
        return sysOperationLog;
    }

    @BeforeEach
    public void initTest() {
        sysOperationLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysOperationLog() throws Exception {
        int databaseSizeBeforeCreate = sysOperationLogRepository.findAll().size();

        // Create the SysOperationLog
        restSysOperationLogMockMvc.perform(post("/api/sys-operation-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysOperationLog)))
            .andExpect(status().isCreated());

        // Validate the SysOperationLog in the database
        List<SysOperationLog> sysOperationLogList = sysOperationLogRepository.findAll();
        assertThat(sysOperationLogList).hasSize(databaseSizeBeforeCreate + 1);
        SysOperationLog testSysOperationLog = sysOperationLogList.get(sysOperationLogList.size() - 1);
        assertThat(testSysOperationLog.getLogType()).isEqualTo(DEFAULT_LOG_TYPE);
        assertThat(testSysOperationLog.getLogName()).isEqualTo(DEFAULT_LOG_NAME);
        assertThat(testSysOperationLog.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testSysOperationLog.getClassName()).isEqualTo(DEFAULT_CLASS_NAME);
        assertThat(testSysOperationLog.getMethod()).isEqualTo(DEFAULT_METHOD);
        assertThat(testSysOperationLog.getSucceed()).isEqualTo(DEFAULT_SUCCEED);
        assertThat(testSysOperationLog.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysOperationLog.getMessage()).isEqualTo(DEFAULT_MESSAGE);
    }

    @Test
    @Transactional
    public void createSysOperationLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysOperationLogRepository.findAll().size();

        // Create the SysOperationLog with an existing ID
        sysOperationLog.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysOperationLogMockMvc.perform(post("/api/sys-operation-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysOperationLog)))
            .andExpect(status().isBadRequest());

        // Validate the SysOperationLog in the database
        List<SysOperationLog> sysOperationLogList = sysOperationLogRepository.findAll();
        assertThat(sysOperationLogList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSysOperationLogs() throws Exception {
        // Initialize the database
        sysOperationLogRepository.saveAndFlush(sysOperationLog);

        // Get all the sysOperationLogList
        restSysOperationLogMockMvc.perform(get("/api/sys-operation-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysOperationLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].logType").value(hasItem(DEFAULT_LOG_TYPE)))
            .andExpect(jsonPath("$.[*].logName").value(hasItem(DEFAULT_LOG_NAME)))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].className").value(hasItem(DEFAULT_CLASS_NAME)))
            .andExpect(jsonPath("$.[*].method").value(hasItem(DEFAULT_METHOD)))
            .andExpect(jsonPath("$.[*].succeed").value(hasItem(DEFAULT_SUCCEED)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE)));
    }
    
    @Test
    @Transactional
    public void getSysOperationLog() throws Exception {
        // Initialize the database
        sysOperationLogRepository.saveAndFlush(sysOperationLog);

        // Get the sysOperationLog
        restSysOperationLogMockMvc.perform(get("/api/sys-operation-logs/{id}", sysOperationLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysOperationLog.getId().intValue()))
            .andExpect(jsonPath("$.logType").value(DEFAULT_LOG_TYPE))
            .andExpect(jsonPath("$.logName").value(DEFAULT_LOG_NAME))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.className").value(DEFAULT_CLASS_NAME))
            .andExpect(jsonPath("$.method").value(DEFAULT_METHOD))
            .andExpect(jsonPath("$.succeed").value(DEFAULT_SUCCEED))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE));
    }

    @Test
    @Transactional
    public void getNonExistingSysOperationLog() throws Exception {
        // Get the sysOperationLog
        restSysOperationLogMockMvc.perform(get("/api/sys-operation-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysOperationLog() throws Exception {
        // Initialize the database
        sysOperationLogService.save(sysOperationLog);

        int databaseSizeBeforeUpdate = sysOperationLogRepository.findAll().size();

        // Update the sysOperationLog
        SysOperationLog updatedSysOperationLog = sysOperationLogRepository.findById(sysOperationLog.getId()).get();
        // Disconnect from session so that the updates on updatedSysOperationLog are not directly saved in db
        em.detach(updatedSysOperationLog);
        updatedSysOperationLog
            .logType(UPDATED_LOG_TYPE)
            .logName(UPDATED_LOG_NAME)
            .userId(UPDATED_USER_ID)
            .className(UPDATED_CLASS_NAME)
            .method(UPDATED_METHOD)
            .succeed(UPDATED_SUCCEED)
            .createTime(UPDATED_CREATE_TIME)
            .message(UPDATED_MESSAGE);

        restSysOperationLogMockMvc.perform(put("/api/sys-operation-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysOperationLog)))
            .andExpect(status().isOk());

        // Validate the SysOperationLog in the database
        List<SysOperationLog> sysOperationLogList = sysOperationLogRepository.findAll();
        assertThat(sysOperationLogList).hasSize(databaseSizeBeforeUpdate);
        SysOperationLog testSysOperationLog = sysOperationLogList.get(sysOperationLogList.size() - 1);
        assertThat(testSysOperationLog.getLogType()).isEqualTo(UPDATED_LOG_TYPE);
        assertThat(testSysOperationLog.getLogName()).isEqualTo(UPDATED_LOG_NAME);
        assertThat(testSysOperationLog.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testSysOperationLog.getClassName()).isEqualTo(UPDATED_CLASS_NAME);
        assertThat(testSysOperationLog.getMethod()).isEqualTo(UPDATED_METHOD);
        assertThat(testSysOperationLog.getSucceed()).isEqualTo(UPDATED_SUCCEED);
        assertThat(testSysOperationLog.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysOperationLog.getMessage()).isEqualTo(UPDATED_MESSAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingSysOperationLog() throws Exception {
        int databaseSizeBeforeUpdate = sysOperationLogRepository.findAll().size();

        // Create the SysOperationLog

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysOperationLogMockMvc.perform(put("/api/sys-operation-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysOperationLog)))
            .andExpect(status().isBadRequest());

        // Validate the SysOperationLog in the database
        List<SysOperationLog> sysOperationLogList = sysOperationLogRepository.findAll();
        assertThat(sysOperationLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysOperationLog() throws Exception {
        // Initialize the database
        sysOperationLogService.save(sysOperationLog);

        int databaseSizeBeforeDelete = sysOperationLogRepository.findAll().size();

        // Delete the sysOperationLog
        restSysOperationLogMockMvc.perform(delete("/api/sys-operation-logs/{id}", sysOperationLog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysOperationLog> sysOperationLogList = sysOperationLogRepository.findAll();
        assertThat(sysOperationLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
