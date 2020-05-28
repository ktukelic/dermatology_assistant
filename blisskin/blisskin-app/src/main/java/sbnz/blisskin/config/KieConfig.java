package sbnz.blisskin.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import javax.enterprise.context.ApplicationScoped;

@Configuration
public class KieConfig {

//    @Bean
//    public KieBase kieBase() {
//        KieServices ks = KieServices.Factory.get();
//
//        KieBaseConfiguration kieBaseConfig = ks.newKieBaseConfiguration();
//        kieBaseConfig.setOption(EventProcessingOption.STREAM);
//
//        KieContainer kContainer = ks
//                .newKieContainer(ks.newReleaseId("sbnz.blisskin", "blisskin-kjar", "0.0.1-SNAPSHOT"));
//        KieScanner kScanner = ks.newKieScanner(kContainer);
//        kScanner.start(10_000);
//        return kContainer.newKieBase(kieBaseConfig);
//    }

    @Bean
    @ApplicationScoped
    public KieContainer kieContainer() {
        final KieServices kServices = KieServices.Factory.get();

        final KieContainer kieContainer = kServices.newKieContainer(kServices.newReleaseId("sbnz.blisskin", "blisskin-kjar", "0.0.1-SNAPSHOT"));
        final KieScanner kieScanner = kServices.newKieScanner(kieContainer);
        kieScanner.start(10000);

        return kieContainer;
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public KieSession kieSession(KieContainer kieContainer) {
        return kieContainer.newKieSession();
    }
}
