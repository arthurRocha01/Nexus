package com.arthurrocha.nexus.infrastructure.config;

import java.util.concurrent.atomic.AtomicReference;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
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
    
    private void updateToken() {    
        int maxAttempsts = 3;
        int attempt = 0;

        while (attempt < maxAttempsts) {
            try {
                String newToken = this.automationClient.fetchGdoorToken();
                this.currentJwtToken.set(newToken);
                System.out.println("Token Gdoor atualizado com sucesso");
                return;
            } catch (Exception e) {
                attempt++;
                System.out.println(
                    "Falha ao atualizar token Gdoor - tentativa " + attempt + " de " + maxAttempsts + "\nErro: " + e.getMessage()
                );

                if (attempt >= maxAttempsts) break;
                try { Thread.sleep(5000); } catch (InterruptedException ie) { Thread.currentThread().interrupt(); }
            }
        }
    }

    public String getCurrentToken() {
        return this.currentJwtToken.get();
    }
}
