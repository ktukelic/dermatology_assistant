package sbnz.blisskin.monitoring.config;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MonitoringKieConfig {

    @Bean(name = "monitoring")
    @Scope
    public KieSession kieSession(KieContainer kieContainer) {
        return kieContainer.newKieSession("monitoring-session");
    }

}

