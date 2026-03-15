package com.arthurrocha.nexus.infrastructure.config;

import java.util.concurrent.atomic.AtomicReference;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.arthurrocha.nexus.infrastructure.client.automation.AutomationClient;

@Component
public class GdoorTokenManager {
    private final AutomationClient automationClient;
    
    private final AtomicReference<String> currentJwtToken = new AtomicReference<>("");
    
    public GdoorTokenManager(AutomationClient automationClient) {
        this.automationClient = automationClient;
    }
    
    @EventListener(ApplicationReadyEvent.class)
    public void fetchTokenOnStartup() {
        this.updateToken();
    }
    
    @Scheduled(fixedRate = 7200000)
    public void fetchTokenScheduled() {
        if (this.currentJwtToken.get().isEmpty()) {
            return;
        }
        
        this.updateToken();
    }
    
    private void updateToken() {
        try {
            String newToken = this.automationClient.fetchGdoorToken();
            this.currentJwtToken.set(newToken);
        } catch (Exception e) {
            System.out.println("Falha ao atualizar token Gdoor: " + e.getMessage());
        }
    }
    
    public String getCurrentToken() {
        return this.currentJwtToken.get();
    }
}
