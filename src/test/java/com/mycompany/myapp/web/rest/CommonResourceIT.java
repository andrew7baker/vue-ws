package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.VuwsmtApp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Test class for the CommonResource REST controller.
 *
 * @see CommonResource
 */
@SpringBootTest(classes = VuwsmtApp.class)
public class CommonResourceIT {

    private MockMvc restMockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);


        restMockMvc = MockMvcBuilders
            .standaloneSetup()
            .build();
    }

    /**
     * Test defaultAction
     */
    @Test
    public void testDefaultAction() throws Exception {
        restMockMvc.perform(get("/api/common/default-action"))
            .andExpect(status().isOk());
    }
}
