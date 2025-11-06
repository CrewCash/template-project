package com.crewcash.template_project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MockIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void healthAndReadyEndpointsWork() {
        ResponseEntity<Map> health = restTemplate.getForEntity("/health", Map.class);
        assertThat(health.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(health.getBody()).containsEntry("status", "UP");

        ResponseEntity<Map> alive = restTemplate.getForEntity("/alive", Map.class);
        assertThat(alive.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(alive.getBody()).containsKey("uptimeMillis");

        // toggle readiness off
        restTemplate.postForEntity("/ready/false", null, Void.class);
        ResponseEntity<Map> notReady = restTemplate.getForEntity("/ready", Map.class);
        assertThat(notReady.getStatusCode().is5xxServerError() || notReady.getStatusCode().is4xxClientError() || notReady.getStatusCodeValue()==503).isTrue();

        // toggle readiness on
        restTemplate.postForEntity("/ready/true", null, Void.class);
        ResponseEntity<Map> ready = restTemplate.getForEntity("/ready", Map.class);
        assertThat(ready.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(ready.getBody()).containsEntry("status", "READY");
    }

}
