package sbnz.blisskin.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.blisskin.model.TestItem;

@Service
public class TestService {
    private static Logger log = LoggerFactory.getLogger(TestService.class);
    private final KieContainer kieContainer;

    @Autowired
    public TestService(KieContainer kieContainer) {
        log.info("Initialising test session.");
        this.kieContainer = kieContainer;
    }

    public TestItem updateTestItemName(TestItem testItem) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(testItem);
        kieSession.fireAllRules();
        kieSession.dispose();
        return testItem;
    }
}
