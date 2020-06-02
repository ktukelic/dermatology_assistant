package sbnz.blisskin.config;

import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import javax.enterprise.context.ApplicationScoped;

@Configuration
public class KieConfig {

    @Value("${kjar.groupId}")
    private String groupId;

    @Value("${kjar.artifactId}")
    private String artifactId;

    @Value("${kjar.version}")
    private String version;

    @Bean
    @ApplicationScoped
    public KieContainer kieContainer() {
        final KieServices kServices = KieServices.Factory.get();

        KieBaseConfiguration kieBaseConfig = kServices.newKieBaseConfiguration();
        kieBaseConfig.setOption(EventProcessingOption.STREAM);

        final KieContainer kieContainer = kServices.newKieContainer(kServices.newReleaseId(groupId, artifactId, version));
        final KieScanner kieScanner = kServices.newKieScanner(kieContainer);
        kieScanner.start(10000);

        return kieContainer;
    }

    @Bean(name = "reasoning")
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public KieSession kieSession(KieContainer kieContainer) {
        return kieContainer.newKieSession("reasoning-session");
    }
}
