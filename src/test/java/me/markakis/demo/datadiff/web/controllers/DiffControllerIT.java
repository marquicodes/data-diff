package me.markakis.demo.datadiff.web.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import me.markakis.demo.datadiff.DataDiffApp;

/**
 * Integration tests of DiffController class.
 * 
 * @author marqui
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataDiffApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DiffControllerIT {

    @LocalServerPort
    private int              port;

    private HttpHeaders      headers      = new HttpHeaders();
    private TestRestTemplate restTemplate = new TestRestTemplate();

    /**
     * Tests the left end-point when data are posted.
     */
    @Test
    public void testReceiveLeft() {
        Integer id = 1;
        HttpEntity<String> entity = new HttpEntity<>("bGVmdCBzaWRlIGRhdGE=", headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/v1/diff/" + id + "/left"),
                HttpMethod.POST, entity, String.class);
        assertNotNull(response);
        assertResponse(response.getBody(), "OK", "Success");
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    /**
     * Tests the right end-point when data are posted.
     */
    @Test
    public void testReceiveRight() {
        Integer id = 2;
        HttpEntity<String> entity = new HttpEntity<>("cmlnaHQgc2lkZSBkYXRhIA==", headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/v1/diff/" + id + "/right"),
                HttpMethod.POST, entity, String.class);
        assertNotNull(response);
        assertResponse(response.getBody(), "OK", "Success");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    /**
     * Tests the diff end-point when no data have been submitted.
     */
    @Test
    public void testResult_NoDataFound() {
        Integer id = 3;
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/v1/diff/" + id), HttpMethod.GET,
                entity, String.class);
        assertNotNull(response);
        assertResponse(response.getBody(), "OK", "No available data for the requested id.");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    /**
     * Parses the supplied response body and asserts that status and message
     * equal to the expected values.
     * 
     * @param responseBodyBody
     *            the body of the response.
     * @param status
     *            the expected status text.
     * @param message
     *            the expected message.
     */
    private void assertResponse(String responseBodyBody, String status, String message) {
        String[] parts = responseBodyBody.substring(1, responseBodyBody.length() - 1).split(",");
        assertEquals("\"status\":\"" + status + "\"", parts[0]);
        assertEquals("\"message\":\"" + message + "\"", parts[2]);
    }

}
