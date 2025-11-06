package com.crewcash.template_project.mock;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MockController {

    private final MockService mockService;

    public MockController(MockService mockService) {
        this.mockService = mockService;
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> body = new HashMap<>();
        body.put("status", "UP");
        return ResponseEntity.ok(body);
    }

    @GetMapping("/ready")
    public ResponseEntity<Map<String, String>> ready() {
        if (mockService.isReady()) {
            Map<String, String> body = new HashMap<>();
            body.put("status", "READY");
            return ResponseEntity.ok(body);
        } else {
            Map<String, String> body = new HashMap<>();
            body.put("status", "NOT_READY");
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(body);
        }
    }

    @PostMapping("/ready/{state}")
    public ResponseEntity<Void> setReady(@PathVariable("state") boolean state) {
        mockService.setReady(state);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/alive")
    public ResponseEntity<Map<String, Object>> alive() {
        Map<String, Object> body = new HashMap<>();
        body.put("uptimeMillis", ManagementFactory.getRuntimeMXBean().getUptime());
        body.put("alive", true);
        return ResponseEntity.ok(body);
    }

}
