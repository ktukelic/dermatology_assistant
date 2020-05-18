package sbnz.blisskin.config;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KieConfig {

    @Bean
    public KieBase kieBase() {
        KieServices ks = KieServices.Factory.get();

        KieBaseConfiguration kieBaseConfig = ks.newKieBaseConfiguration();
        kieBaseConfig.setOption(EventProcessingOption.STREAM);

        KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId("sbnz.blisskin", "blisskin-kjar", "0.0.1-SNAPSHOT"));
        KieScanner kScanner = ks.newKieScanner(kContainer);
        kScanner.start(10_000);
        return kContainer.newKieBase(kieBaseConfig);
    }
}
