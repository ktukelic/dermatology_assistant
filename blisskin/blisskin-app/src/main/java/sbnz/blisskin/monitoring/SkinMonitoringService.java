package sbnz.blisskin.monitoring;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sbnz.blisskin.monitoring.model.CloselySupervisedPatient;

@Service
public class SkinMonitoringService {

    private final KieSession kieSession;
    private final SimpMessagingTemplate template;

    public SkinMonitoringService(@Qualifier("monitoring") KieSession kieSession, SimpMessagingTemplate template) {
        this.kieSession = kieSession;
        this.template = template;

        kieSession.setGlobal("service", this);


    }

    @Scheduled(fixedDelay = 10000)
    public void testEvent() {
        kieSession.insert(new CloselySupervisedPatient("Teodora"));
        fireAllRules();
    }

//    @MessageMapping("/smtng")
//    @SendTo("/monitoring")
//    public String notify(@Payload String message) {
//        return message;
//    }

    public void notify(String message) {
        template.convertAndSend("/monitoring", message);
    }

    private void fireAllRules() {
        kieSession.getAgenda().getAgendaGroup("skin-monitoring").setFocus();
        kieSession.fireAllRules();
    }


}
