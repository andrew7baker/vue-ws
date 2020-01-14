package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.VuwsmtApp;
import com.mycompany.myapp.domain.Production;
import com.mycompany.myapp.repository.ProductionRepository;
import com.mycompany.myapp.service.ProductionService;
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
 * Integration tests for the {@link ProductionResource} REST controller.
 */
@SpringBootTest(classes = VuwsmtApp.class)
public class ProductionResourceIT {

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    private static final Integer DEFAULT_POWER_TIME = 1;
    private static final Integer UPDATED_POWER_TIME = 2;

    private static final Integer DEFAULT_PLACE_TIME = 1;
    private static final Integer UPDATED_PLACE_TIME = 2;

    private static final Integer DEFAULT_WAIT_TIME = 1;
    private static final Integer UPDATED_WAIT_TIME = 2;

    private static final Integer DEFAULT_RUN_TIME = 1;
    private static final Integer UPDATED_RUN_TIME = 2;

    private static final Integer DEFAULT_IDLE_TIME = 1;
    private static final Integer UPDATED_IDLE_TIME = 2;

    private static final Integer DEFAULT_IN_WAIT_TIME = 1;
    private static final Integer UPDATED_IN_WAIT_TIME = 2;

    private static final Integer DEFAULT_WRONG_STOP_TIME = 1;
    private static final Integer UPDATED_WRONG_STOP_TIME = 2;

    private static final Integer DEFAULT_ERROR_STOP_T_IME = 1;
    private static final Integer UPDATED_ERROR_STOP_T_IME = 2;

    private static final Integer DEFAULT_WRONG_STOP_COUNT = 1;
    private static final Integer UPDATED_WRONG_STOP_COUNT = 2;

    private static final Integer DEFAULT_ERROR_STOP_COUNT = 1;
    private static final Integer UPDATED_ERROR_STOP_COUNT = 2;

    private static final Integer DEFAULT_PANEL_IN_COUNT = 1;
    private static final Integer UPDATED_PANEL_IN_COUNT = 2;

    private static final Integer DEFAULT_PANEL_COUNT = 1;
    private static final Integer UPDATED_PANEL_COUNT = 2;

    private static final Integer DEFAULT_P_CB_COUNT = 1;
    private static final Integer UPDATED_P_CB_COUNT = 2;

    private static final Integer DEFAULT_ERROR_PCB = 1;
    private static final Integer UPDATED_ERROR_PCB = 2;

    private static final Integer DEFAULT_SKIP_PCB = 1;
    private static final Integer UPDATED_SKIP_PCB = 2;

    private static final Long DEFAULT_OPERATION_RATE = 1L;
    private static final Long UPDATED_OPERATION_RATE = 2L;

    private static final Long DEFAULT_PLACEMENT_RATE = 1L;
    private static final Long UPDATED_PLACEMENT_RATE = 2L;

    private static final Long DEFAULT_MEAN_TIME = 1L;
    private static final Long UPDATED_MEAN_TIME = 2L;

    private static final Long DEFAULT_REAL_TIME = 1L;
    private static final Long UPDATED_REAL_TIME = 2L;

    private static final Long DEFAULT_TRANSFER_TIME = 1L;
    private static final Long UPDATED_TRANSFER_TIME = 2L;

    private static final Integer DEFAULT_PLACE_COUNT = 1;
    private static final Integer UPDATED_PLACE_COUNT = 2;

    private static final Long DEFAULT_THE_EFFICIENCY = 1L;
    private static final Long UPDATED_THE_EFFICIENCY = 2L;

    @Autowired
    private ProductionRepository productionRepository;

    @Autowired
    private ProductionService productionService;

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

    private MockMvc restProductionMockMvc;

    private Production production;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductionResource productionResource = new ProductionResource(productionService);
        this.restProductionMockMvc = MockMvcBuilders.standaloneSetup(productionResource)
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
    public static Production createEntity(EntityManager em) {
        Production production = new Production()
            .version(DEFAULT_VERSION)
            .powerTime(DEFAULT_POWER_TIME)
            .placeTime(DEFAULT_PLACE_TIME)
            .waitTime(DEFAULT_WAIT_TIME)
            .runTime(DEFAULT_RUN_TIME)
            .idleTime(DEFAULT_IDLE_TIME)
            .inWaitTime(DEFAULT_IN_WAIT_TIME)
            .wrongStopTime(DEFAULT_WRONG_STOP_TIME)
            .errorStopTIme(DEFAULT_ERROR_STOP_T_IME)
            .wrongStopCount(DEFAULT_WRONG_STOP_COUNT)
            .errorStopCount(DEFAULT_ERROR_STOP_COUNT)
            .panelInCount(DEFAULT_PANEL_IN_COUNT)
            .panelCount(DEFAULT_PANEL_COUNT)
            .pCBCount(DEFAULT_P_CB_COUNT)
            .errorPcb(DEFAULT_ERROR_PCB)
            .skipPCB(DEFAULT_SKIP_PCB)
            .operationRate(DEFAULT_OPERATION_RATE)
            .placementRate(DEFAULT_PLACEMENT_RATE)
            .meanTime(DEFAULT_MEAN_TIME)
            .realTime(DEFAULT_REAL_TIME)
            .transferTime(DEFAULT_TRANSFER_TIME)
            .placeCount(DEFAULT_PLACE_COUNT)
            .theEfficiency(DEFAULT_THE_EFFICIENCY);
        return production;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Production createUpdatedEntity(EntityManager em) {
        Production production = new Production()
            .version(UPDATED_VERSION)
            .powerTime(UPDATED_POWER_TIME)
            .placeTime(UPDATED_PLACE_TIME)
            .waitTime(UPDATED_WAIT_TIME)
            .runTime(UPDATED_RUN_TIME)
            .idleTime(UPDATED_IDLE_TIME)
            .inWaitTime(UPDATED_IN_WAIT_TIME)
            .wrongStopTime(UPDATED_WRONG_STOP_TIME)
            .errorStopTIme(UPDATED_ERROR_STOP_T_IME)
            .wrongStopCount(UPDATED_WRONG_STOP_COUNT)
            .errorStopCount(UPDATED_ERROR_STOP_COUNT)
            .panelInCount(UPDATED_PANEL_IN_COUNT)
            .panelCount(UPDATED_PANEL_COUNT)
            .pCBCount(UPDATED_P_CB_COUNT)
            .errorPcb(UPDATED_ERROR_PCB)
            .skipPCB(UPDATED_SKIP_PCB)
            .operationRate(UPDATED_OPERATION_RATE)
            .placementRate(UPDATED_PLACEMENT_RATE)
            .meanTime(UPDATED_MEAN_TIME)
            .realTime(UPDATED_REAL_TIME)
            .transferTime(UPDATED_TRANSFER_TIME)
            .placeCount(UPDATED_PLACE_COUNT)
            .theEfficiency(UPDATED_THE_EFFICIENCY);
        return production;
    }

    @BeforeEach
    public void initTest() {
        production = createEntity(em);
    }

    @Test
    @Transactional
    public void createProduction() throws Exception {
        int databaseSizeBeforeCreate = productionRepository.findAll().size();

        // Create the Production
        restProductionMockMvc.perform(post("/api/productions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(production)))
            .andExpect(status().isCreated());

        // Validate the Production in the database
        List<Production> productionList = productionRepository.findAll();
        assertThat(productionList).hasSize(databaseSizeBeforeCreate + 1);
        Production testProduction = productionList.get(productionList.size() - 1);
        assertThat(testProduction.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testProduction.getPowerTime()).isEqualTo(DEFAULT_POWER_TIME);
        assertThat(testProduction.getPlaceTime()).isEqualTo(DEFAULT_PLACE_TIME);
        assertThat(testProduction.getWaitTime()).isEqualTo(DEFAULT_WAIT_TIME);
        assertThat(testProduction.getRunTime()).isEqualTo(DEFAULT_RUN_TIME);
        assertThat(testProduction.getIdleTime()).isEqualTo(DEFAULT_IDLE_TIME);
        assertThat(testProduction.getInWaitTime()).isEqualTo(DEFAULT_IN_WAIT_TIME);
        assertThat(testProduction.getWrongStopTime()).isEqualTo(DEFAULT_WRONG_STOP_TIME);
        assertThat(testProduction.getErrorStopTIme()).isEqualTo(DEFAULT_ERROR_STOP_T_IME);
        assertThat(testProduction.getWrongStopCount()).isEqualTo(DEFAULT_WRONG_STOP_COUNT);
        assertThat(testProduction.getErrorStopCount()).isEqualTo(DEFAULT_ERROR_STOP_COUNT);
        assertThat(testProduction.getPanelInCount()).isEqualTo(DEFAULT_PANEL_IN_COUNT);
        assertThat(testProduction.getPanelCount()).isEqualTo(DEFAULT_PANEL_COUNT);
        assertThat(testProduction.getpCBCount()).isEqualTo(DEFAULT_P_CB_COUNT);
        assertThat(testProduction.getErrorPcb()).isEqualTo(DEFAULT_ERROR_PCB);
        assertThat(testProduction.getSkipPCB()).isEqualTo(DEFAULT_SKIP_PCB);
        assertThat(testProduction.getOperationRate()).isEqualTo(DEFAULT_OPERATION_RATE);
        assertThat(testProduction.getPlacementRate()).isEqualTo(DEFAULT_PLACEMENT_RATE);
        assertThat(testProduction.getMeanTime()).isEqualTo(DEFAULT_MEAN_TIME);
        assertThat(testProduction.getRealTime()).isEqualTo(DEFAULT_REAL_TIME);
        assertThat(testProduction.getTransferTime()).isEqualTo(DEFAULT_TRANSFER_TIME);
        assertThat(testProduction.getPlaceCount()).isEqualTo(DEFAULT_PLACE_COUNT);
        assertThat(testProduction.getTheEfficiency()).isEqualTo(DEFAULT_THE_EFFICIENCY);
    }

    @Test
    @Transactional
    public void createProductionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productionRepository.findAll().size();

        // Create the Production with an existing ID
        production.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductionMockMvc.perform(post("/api/productions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(production)))
            .andExpect(status().isBadRequest());

        // Validate the Production in the database
        List<Production> productionList = productionRepository.findAll();
        assertThat(productionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProductions() throws Exception {
        // Initialize the database
        productionRepository.saveAndFlush(production);

        // Get all the productionList
        restProductionMockMvc.perform(get("/api/productions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(production.getId().intValue())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].powerTime").value(hasItem(DEFAULT_POWER_TIME)))
            .andExpect(jsonPath("$.[*].placeTime").value(hasItem(DEFAULT_PLACE_TIME)))
            .andExpect(jsonPath("$.[*].waitTime").value(hasItem(DEFAULT_WAIT_TIME)))
            .andExpect(jsonPath("$.[*].runTime").value(hasItem(DEFAULT_RUN_TIME)))
            .andExpect(jsonPath("$.[*].idleTime").value(hasItem(DEFAULT_IDLE_TIME)))
            .andExpect(jsonPath("$.[*].inWaitTime").value(hasItem(DEFAULT_IN_WAIT_TIME)))
            .andExpect(jsonPath("$.[*].wrongStopTime").value(hasItem(DEFAULT_WRONG_STOP_TIME)))
            .andExpect(jsonPath("$.[*].errorStopTIme").value(hasItem(DEFAULT_ERROR_STOP_T_IME)))
            .andExpect(jsonPath("$.[*].wrongStopCount").value(hasItem(DEFAULT_WRONG_STOP_COUNT)))
            .andExpect(jsonPath("$.[*].errorStopCount").value(hasItem(DEFAULT_ERROR_STOP_COUNT)))
            .andExpect(jsonPath("$.[*].panelInCount").value(hasItem(DEFAULT_PANEL_IN_COUNT)))
            .andExpect(jsonPath("$.[*].panelCount").value(hasItem(DEFAULT_PANEL_COUNT)))
            .andExpect(jsonPath("$.[*].pCBCount").value(hasItem(DEFAULT_P_CB_COUNT)))
            .andExpect(jsonPath("$.[*].errorPcb").value(hasItem(DEFAULT_ERROR_PCB)))
            .andExpect(jsonPath("$.[*].skipPCB").value(hasItem(DEFAULT_SKIP_PCB)))
            .andExpect(jsonPath("$.[*].operationRate").value(hasItem(DEFAULT_OPERATION_RATE.intValue())))
            .andExpect(jsonPath("$.[*].placementRate").value(hasItem(DEFAULT_PLACEMENT_RATE.intValue())))
            .andExpect(jsonPath("$.[*].meanTime").value(hasItem(DEFAULT_MEAN_TIME.intValue())))
            .andExpect(jsonPath("$.[*].realTime").value(hasItem(DEFAULT_REAL_TIME.intValue())))
            .andExpect(jsonPath("$.[*].transferTime").value(hasItem(DEFAULT_TRANSFER_TIME.intValue())))
            .andExpect(jsonPath("$.[*].placeCount").value(hasItem(DEFAULT_PLACE_COUNT)))
            .andExpect(jsonPath("$.[*].theEfficiency").value(hasItem(DEFAULT_THE_EFFICIENCY.intValue())));
    }
    
    @Test
    @Transactional
    public void getProduction() throws Exception {
        // Initialize the database
        productionRepository.saveAndFlush(production);

        // Get the production
        restProductionMockMvc.perform(get("/api/productions/{id}", production.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(production.getId().intValue()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION))
            .andExpect(jsonPath("$.powerTime").value(DEFAULT_POWER_TIME))
            .andExpect(jsonPath("$.placeTime").value(DEFAULT_PLACE_TIME))
            .andExpect(jsonPath("$.waitTime").value(DEFAULT_WAIT_TIME))
            .andExpect(jsonPath("$.runTime").value(DEFAULT_RUN_TIME))
            .andExpect(jsonPath("$.idleTime").value(DEFAULT_IDLE_TIME))
            .andExpect(jsonPath("$.inWaitTime").value(DEFAULT_IN_WAIT_TIME))
            .andExpect(jsonPath("$.wrongStopTime").value(DEFAULT_WRONG_STOP_TIME))
            .andExpect(jsonPath("$.errorStopTIme").value(DEFAULT_ERROR_STOP_T_IME))
            .andExpect(jsonPath("$.wrongStopCount").value(DEFAULT_WRONG_STOP_COUNT))
            .andExpect(jsonPath("$.errorStopCount").value(DEFAULT_ERROR_STOP_COUNT))
            .andExpect(jsonPath("$.panelInCount").value(DEFAULT_PANEL_IN_COUNT))
            .andExpect(jsonPath("$.panelCount").value(DEFAULT_PANEL_COUNT))
            .andExpect(jsonPath("$.pCBCount").value(DEFAULT_P_CB_COUNT))
            .andExpect(jsonPath("$.errorPcb").value(DEFAULT_ERROR_PCB))
            .andExpect(jsonPath("$.skipPCB").value(DEFAULT_SKIP_PCB))
            .andExpect(jsonPath("$.operationRate").value(DEFAULT_OPERATION_RATE.intValue()))
            .andExpect(jsonPath("$.placementRate").value(DEFAULT_PLACEMENT_RATE.intValue()))
            .andExpect(jsonPath("$.meanTime").value(DEFAULT_MEAN_TIME.intValue()))
            .andExpect(jsonPath("$.realTime").value(DEFAULT_REAL_TIME.intValue()))
            .andExpect(jsonPath("$.transferTime").value(DEFAULT_TRANSFER_TIME.intValue()))
            .andExpect(jsonPath("$.placeCount").value(DEFAULT_PLACE_COUNT))
            .andExpect(jsonPath("$.theEfficiency").value(DEFAULT_THE_EFFICIENCY.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProduction() throws Exception {
        // Get the production
        restProductionMockMvc.perform(get("/api/productions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProduction() throws Exception {
        // Initialize the database
        productionService.save(production);

        int databaseSizeBeforeUpdate = productionRepository.findAll().size();

        // Update the production
        Production updatedProduction = productionRepository.findById(production.getId()).get();
        // Disconnect from session so that the updates on updatedProduction are not directly saved in db
        em.detach(updatedProduction);
        updatedProduction
            .version(UPDATED_VERSION)
            .powerTime(UPDATED_POWER_TIME)
            .placeTime(UPDATED_PLACE_TIME)
            .waitTime(UPDATED_WAIT_TIME)
            .runTime(UPDATED_RUN_TIME)
            .idleTime(UPDATED_IDLE_TIME)
            .inWaitTime(UPDATED_IN_WAIT_TIME)
            .wrongStopTime(UPDATED_WRONG_STOP_TIME)
            .errorStopTIme(UPDATED_ERROR_STOP_T_IME)
            .wrongStopCount(UPDATED_WRONG_STOP_COUNT)
            .errorStopCount(UPDATED_ERROR_STOP_COUNT)
            .panelInCount(UPDATED_PANEL_IN_COUNT)
            .panelCount(UPDATED_PANEL_COUNT)
            .pCBCount(UPDATED_P_CB_COUNT)
            .errorPcb(UPDATED_ERROR_PCB)
            .skipPCB(UPDATED_SKIP_PCB)
            .operationRate(UPDATED_OPERATION_RATE)
            .placementRate(UPDATED_PLACEMENT_RATE)
            .meanTime(UPDATED_MEAN_TIME)
            .realTime(UPDATED_REAL_TIME)
            .transferTime(UPDATED_TRANSFER_TIME)
            .placeCount(UPDATED_PLACE_COUNT)
            .theEfficiency(UPDATED_THE_EFFICIENCY);

        restProductionMockMvc.perform(put("/api/productions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProduction)))
            .andExpect(status().isOk());

        // Validate the Production in the database
        List<Production> productionList = productionRepository.findAll();
        assertThat(productionList).hasSize(databaseSizeBeforeUpdate);
        Production testProduction = productionList.get(productionList.size() - 1);
        assertThat(testProduction.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testProduction.getPowerTime()).isEqualTo(UPDATED_POWER_TIME);
        assertThat(testProduction.getPlaceTime()).isEqualTo(UPDATED_PLACE_TIME);
        assertThat(testProduction.getWaitTime()).isEqualTo(UPDATED_WAIT_TIME);
        assertThat(testProduction.getRunTime()).isEqualTo(UPDATED_RUN_TIME);
        assertThat(testProduction.getIdleTime()).isEqualTo(UPDATED_IDLE_TIME);
        assertThat(testProduction.getInWaitTime()).isEqualTo(UPDATED_IN_WAIT_TIME);
        assertThat(testProduction.getWrongStopTime()).isEqualTo(UPDATED_WRONG_STOP_TIME);
        assertThat(testProduction.getErrorStopTIme()).isEqualTo(UPDATED_ERROR_STOP_T_IME);
        assertThat(testProduction.getWrongStopCount()).isEqualTo(UPDATED_WRONG_STOP_COUNT);
        assertThat(testProduction.getErrorStopCount()).isEqualTo(UPDATED_ERROR_STOP_COUNT);
        assertThat(testProduction.getPanelInCount()).isEqualTo(UPDATED_PANEL_IN_COUNT);
        assertThat(testProduction.getPanelCount()).isEqualTo(UPDATED_PANEL_COUNT);
        assertThat(testProduction.getpCBCount()).isEqualTo(UPDATED_P_CB_COUNT);
        assertThat(testProduction.getErrorPcb()).isEqualTo(UPDATED_ERROR_PCB);
        assertThat(testProduction.getSkipPCB()).isEqualTo(UPDATED_SKIP_PCB);
        assertThat(testProduction.getOperationRate()).isEqualTo(UPDATED_OPERATION_RATE);
        assertThat(testProduction.getPlacementRate()).isEqualTo(UPDATED_PLACEMENT_RATE);
        assertThat(testProduction.getMeanTime()).isEqualTo(UPDATED_MEAN_TIME);
        assertThat(testProduction.getRealTime()).isEqualTo(UPDATED_REAL_TIME);
        assertThat(testProduction.getTransferTime()).isEqualTo(UPDATED_TRANSFER_TIME);
        assertThat(testProduction.getPlaceCount()).isEqualTo(UPDATED_PLACE_COUNT);
        assertThat(testProduction.getTheEfficiency()).isEqualTo(UPDATED_THE_EFFICIENCY);
    }

    @Test
    @Transactional
    public void updateNonExistingProduction() throws Exception {
        int databaseSizeBeforeUpdate = productionRepository.findAll().size();

        // Create the Production

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductionMockMvc.perform(put("/api/productions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(production)))
            .andExpect(status().isBadRequest());

        // Validate the Production in the database
        List<Production> productionList = productionRepository.findAll();
        assertThat(productionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProduction() throws Exception {
        // Initialize the database
        productionService.save(production);

        int databaseSizeBeforeDelete = productionRepository.findAll().size();

        // Delete the production
        restProductionMockMvc.perform(delete("/api/productions/{id}", production.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Production> productionList = productionRepository.findAll();
        assertThat(productionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
