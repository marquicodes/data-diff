package me.markakis.demo.datadiff.web.controllers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import me.markakis.demo.datadiff.services.DiffService;
import me.markakis.demo.datadiff.web.validators.DataRequestValidator;

/**
 * Tests responses of DiffController using MockMvc.
 * 
 * @author marqui
 */
@RunWith(SpringRunner.class)
@WebMvcTest(DiffController.class)
public class DiffControllerTest {

    @Autowired
    private MockMvc              mvc;

    @MockBean
    private DataRequestValidator dataRequestValidator;

    @MockBean
    private DiffService          diffService;

    /**
     * Tests the response when data are posted to the left end-point.
     * 
     * @throws Exception
     */
    @Test
    public void testReceiveLeft() throws Exception {
        String data = "bGVmdCBzaWRlIGRhdGE=";
        mvc.perform(post("/v1/diff/11/left").contentType(MediaType.APPLICATION_JSON_UTF8).content(data))
                .andExpect(status().isOk()).andExpect(jsonPath("$.message", is("Success"))).andReturn();
    }

    /**
     * Tests the response when data are posted to the right end-point.
     * 
     * @throws Exception
     */
    @Test
    public void testReceiveRight() throws Exception {
        String data = "cmlnaHQgc2lkZSBkYXRhIA==";
        mvc.perform(post("/v1/diff/11/right").contentType(MediaType.APPLICATION_JSON_UTF8).content(data))
                .andExpect(status().isOk()).andExpect(jsonPath("$.message", is("Success"))).andReturn();
    }

    /**
     * Tests the response when get the comparison results.
     * 
     * @throws Exception
     */
    @Test
    public void testResult() throws Exception {
        mvc.perform(get("/v1/diff/11").contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
                .andReturn();
    }

}
