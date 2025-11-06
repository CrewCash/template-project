package com.crewcash.template_project.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class MockService {

    private static final Logger logger = LoggerFactory.getLogger(MockService.class);

    private final AtomicBoolean ready = new AtomicBoolean(true);

    public boolean isReady() {
        return ready.get();
    }

    public void setReady(boolean value) {
        ready.set(value);
        logger.info("Readiness set to {}", value);
    }

    // Keep the application visibly active for infrastructure tests by emitting a heartbeat
    @Scheduled(fixedRate = 30_000)
    public void heartbeat() {
        logger.info("mock-service heartbeat: ready={}", ready.get());
    }

}
