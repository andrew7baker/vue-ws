package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.VuwsmtApp;
import com.mycompany.myapp.domain.SysFileInfo;
import com.mycompany.myapp.repository.SysFileInfoRepository;
import com.mycompany.myapp.service.SysFileInfoService;
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
 * Integration tests for the {@link SysFileInfoResource} REST controller.
 */
@SpringBootTest(classes = VuwsmtApp.class)
public class SysFileInfoResourceIT {

    private static final String DEFAULT_FILE_BUCKET = "AAAAAAAAAA";
    private static final String UPDATED_FILE_BUCKET = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_SUFFIX = "AAAAAAAAAA";
    private static final String UPDATED_FILE_SUFFIX = "BBBBBBBBBB";

    private static final Long DEFAULT_FILE_SIZE_KB = 1L;
    private static final Long UPDATED_FILE_SIZE_KB = 2L;

    private static final String DEFAULT_FINAL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FINAL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_FILE_PATH = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_CREATE_USER = 1L;
    private static final Long UPDATED_CREATE_USER = 2L;

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_UPDATE_USER = 1L;
    private static final Long UPDATED_UPDATE_USER = 2L;

    @Autowired
    private SysFileInfoRepository sysFileInfoRepository;

    @Autowired
    private SysFileInfoService sysFileInfoService;

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

    private MockMvc restSysFileInfoMockMvc;

    private SysFileInfo sysFileInfo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysFileInfoResource sysFileInfoResource = new SysFileInfoResource(sysFileInfoService);
        this.restSysFileInfoMockMvc = MockMvcBuilders.standaloneSetup(sysFileInfoResource)
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
    public static SysFileInfo createEntity(EntityManager em) {
        SysFileInfo sysFileInfo = new SysFileInfo()
            .fileBucket(DEFAULT_FILE_BUCKET)
            .fileName(DEFAULT_FILE_NAME)
            .fileSuffix(DEFAULT_FILE_SUFFIX)
            .fileSizeKb(DEFAULT_FILE_SIZE_KB)
            .finalName(DEFAULT_FINAL_NAME)
            .filePath(DEFAULT_FILE_PATH)
            .createTime(DEFAULT_CREATE_TIME)
            .createUser(DEFAULT_CREATE_USER)
            .updateTime(DEFAULT_UPDATE_TIME)
            .updateUser(DEFAULT_UPDATE_USER);
        return sysFileInfo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysFileInfo createUpdatedEntity(EntityManager em) {
        SysFileInfo sysFileInfo = new SysFileInfo()
            .fileBucket(UPDATED_FILE_BUCKET)
            .fileName(UPDATED_FILE_NAME)
            .fileSuffix(UPDATED_FILE_SUFFIX)
            .fileSizeKb(UPDATED_FILE_SIZE_KB)
            .finalName(UPDATED_FINAL_NAME)
            .filePath(UPDATED_FILE_PATH)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER);
        return sysFileInfo;
    }

    @BeforeEach
    public void initTest() {
        sysFileInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysFileInfo() throws Exception {
        int databaseSizeBeforeCreate = sysFileInfoRepository.findAll().size();

        // Create the SysFileInfo
        restSysFileInfoMockMvc.perform(post("/api/sys-file-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysFileInfo)))
            .andExpect(status().isCreated());

        // Validate the SysFileInfo in the database
        List<SysFileInfo> sysFileInfoList = sysFileInfoRepository.findAll();
        assertThat(sysFileInfoList).hasSize(databaseSizeBeforeCreate + 1);
        SysFileInfo testSysFileInfo = sysFileInfoList.get(sysFileInfoList.size() - 1);
        assertThat(testSysFileInfo.getFileBucket()).isEqualTo(DEFAULT_FILE_BUCKET);
        assertThat(testSysFileInfo.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testSysFileInfo.getFileSuffix()).isEqualTo(DEFAULT_FILE_SUFFIX);
        assertThat(testSysFileInfo.getFileSizeKb()).isEqualTo(DEFAULT_FILE_SIZE_KB);
        assertThat(testSysFileInfo.getFinalName()).isEqualTo(DEFAULT_FINAL_NAME);
        assertThat(testSysFileInfo.getFilePath()).isEqualTo(DEFAULT_FILE_PATH);
        assertThat(testSysFileInfo.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysFileInfo.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testSysFileInfo.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testSysFileInfo.getUpdateUser()).isEqualTo(DEFAULT_UPDATE_USER);
    }

    @Test
    @Transactional
    public void createSysFileInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysFileInfoRepository.findAll().size();

        // Create the SysFileInfo with an existing ID
        sysFileInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysFileInfoMockMvc.perform(post("/api/sys-file-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysFileInfo)))
            .andExpect(status().isBadRequest());

        // Validate the SysFileInfo in the database
        List<SysFileInfo> sysFileInfoList = sysFileInfoRepository.findAll();
        assertThat(sysFileInfoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSysFileInfos() throws Exception {
        // Initialize the database
        sysFileInfoRepository.saveAndFlush(sysFileInfo);

        // Get all the sysFileInfoList
        restSysFileInfoMockMvc.perform(get("/api/sys-file-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysFileInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].fileBucket").value(hasItem(DEFAULT_FILE_BUCKET)))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME)))
            .andExpect(jsonPath("$.[*].fileSuffix").value(hasItem(DEFAULT_FILE_SUFFIX)))
            .andExpect(jsonPath("$.[*].fileSizeKb").value(hasItem(DEFAULT_FILE_SIZE_KB.intValue())))
            .andExpect(jsonPath("$.[*].finalName").value(hasItem(DEFAULT_FINAL_NAME)))
            .andExpect(jsonPath("$.[*].filePath").value(hasItem(DEFAULT_FILE_PATH)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER.intValue())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateUser").value(hasItem(DEFAULT_UPDATE_USER.intValue())));
    }
    
    @Test
    @Transactional
    public void getSysFileInfo() throws Exception {
        // Initialize the database
        sysFileInfoRepository.saveAndFlush(sysFileInfo);

        // Get the sysFileInfo
        restSysFileInfoMockMvc.perform(get("/api/sys-file-infos/{id}", sysFileInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysFileInfo.getId().intValue()))
            .andExpect(jsonPath("$.fileBucket").value(DEFAULT_FILE_BUCKET))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME))
            .andExpect(jsonPath("$.fileSuffix").value(DEFAULT_FILE_SUFFIX))
            .andExpect(jsonPath("$.fileSizeKb").value(DEFAULT_FILE_SIZE_KB.intValue()))
            .andExpect(jsonPath("$.finalName").value(DEFAULT_FINAL_NAME))
            .andExpect(jsonPath("$.filePath").value(DEFAULT_FILE_PATH))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER.intValue()))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.updateUser").value(DEFAULT_UPDATE_USER.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSysFileInfo() throws Exception {
        // Get the sysFileInfo
        restSysFileInfoMockMvc.perform(get("/api/sys-file-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysFileInfo() throws Exception {
        // Initialize the database
        sysFileInfoService.save(sysFileInfo);

        int databaseSizeBeforeUpdate = sysFileInfoRepository.findAll().size();

        // Update the sysFileInfo
        SysFileInfo updatedSysFileInfo = sysFileInfoRepository.findById(sysFileInfo.getId()).get();
        // Disconnect from session so that the updates on updatedSysFileInfo are not directly saved in db
        em.detach(updatedSysFileInfo);
        updatedSysFileInfo
            .fileBucket(UPDATED_FILE_BUCKET)
            .fileName(UPDATED_FILE_NAME)
            .fileSuffix(UPDATED_FILE_SUFFIX)
            .fileSizeKb(UPDATED_FILE_SIZE_KB)
            .finalName(UPDATED_FINAL_NAME)
            .filePath(UPDATED_FILE_PATH)
            .createTime(UPDATED_CREATE_TIME)
            .createUser(UPDATED_CREATE_USER)
            .updateTime(UPDATED_UPDATE_TIME)
            .updateUser(UPDATED_UPDATE_USER);

        restSysFileInfoMockMvc.perform(put("/api/sys-file-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysFileInfo)))
            .andExpect(status().isOk());

        // Validate the SysFileInfo in the database
        List<SysFileInfo> sysFileInfoList = sysFileInfoRepository.findAll();
        assertThat(sysFileInfoList).hasSize(databaseSizeBeforeUpdate);
        SysFileInfo testSysFileInfo = sysFileInfoList.get(sysFileInfoList.size() - 1);
        assertThat(testSysFileInfo.getFileBucket()).isEqualTo(UPDATED_FILE_BUCKET);
        assertThat(testSysFileInfo.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testSysFileInfo.getFileSuffix()).isEqualTo(UPDATED_FILE_SUFFIX);
        assertThat(testSysFileInfo.getFileSizeKb()).isEqualTo(UPDATED_FILE_SIZE_KB);
        assertThat(testSysFileInfo.getFinalName()).isEqualTo(UPDATED_FINAL_NAME);
        assertThat(testSysFileInfo.getFilePath()).isEqualTo(UPDATED_FILE_PATH);
        assertThat(testSysFileInfo.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysFileInfo.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testSysFileInfo.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testSysFileInfo.getUpdateUser()).isEqualTo(UPDATED_UPDATE_USER);
    }

    @Test
    @Transactional
    public void updateNonExistingSysFileInfo() throws Exception {
        int databaseSizeBeforeUpdate = sysFileInfoRepository.findAll().size();

        // Create the SysFileInfo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysFileInfoMockMvc.perform(put("/api/sys-file-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysFileInfo)))
            .andExpect(status().isBadRequest());

        // Validate the SysFileInfo in the database
        List<SysFileInfo> sysFileInfoList = sysFileInfoRepository.findAll();
        assertThat(sysFileInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysFileInfo() throws Exception {
        // Initialize the database
        sysFileInfoService.save(sysFileInfo);

        int databaseSizeBeforeDelete = sysFileInfoRepository.findAll().size();

        // Delete the sysFileInfo
        restSysFileInfoMockMvc.perform(delete("/api/sys-file-infos/{id}", sysFileInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysFileInfo> sysFileInfoList = sysFileInfoRepository.findAll();
        assertThat(sysFileInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
