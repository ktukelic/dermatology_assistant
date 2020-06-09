package sbnz.blisskin.monitoring;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sbnz.blisskin.model.enumerations.Drug;
import sbnz.blisskin.monitoring.model.AntibioticPatientEvent;
import sbnz.blisskin.monitoring.model.AntifungalPatientEvent;
import sbnz.blisskin.monitoring.model.CloselySupervisedPatient;
import sbnz.blisskin.monitoring.model.SteroidPatientEvent;

import java.util.Random;

@Service
public class SkinMonitoringService {

    private final KieSession kieSession;
    private final SimpMessagingTemplate template;

    private final CloselySupervisedPatient patient1;
    private final CloselySupervisedPatient patient2;
    private final CloselySupervisedPatient patient3;

    Random random;

    public SkinMonitoringService(@Qualifier("monitoring") KieSession kieSession, SimpMessagingTemplate template) {
        this.kieSession = kieSession;
        this.template = template;

//        kieSession.addEventListener(new DebugAgendaEventListener());
        kieSession.setGlobal("service", this);

        patient1 = new CloselySupervisedPatient("Patient 1", Drug.CORTICOSTEROID);
        patient2 = new CloselySupervisedPatient("Patient 2", Drug.ANTIFUNGAL);
        patient3 = new CloselySupervisedPatient("Patient 3", Drug.ANTIBIOTIC);
        kieSession.insert(patient1);
        kieSession.insert(patient2);
        kieSession.insert(patient3);

        random = new Random(System.currentTimeMillis());
    }

//    @Scheduled(fixedDelay = 5000)
    public void run() {
        steroidsSkinAtrophy(patient1);      // Tested
        steroidsRosacea(patient1);

        antifungalPeelingReaction(patient2);
        antifungalBumpsReaction(patient2);

        antibioticsEczemaReaction(patient3);
        antibioticsPainfulRednessReaction(patient3);
    }

    /*
     * CORTICOSTEROIDS problems
     */
    private void steroidsSkinAtrophy(CloselySupervisedPatient patient) {
        SteroidPatientEvent steroidPatientEvent = new SteroidPatientEvent(patient);
        steroidPatientEvent.setPurpura(random.nextBoolean());
        System.out.println(steroidPatientEvent.isPurpura());
        steroidPatientEvent.setBruising(random.nextBoolean());
        System.out.println(steroidPatientEvent.isBruising());
        kieSession.insert(steroidPatientEvent);
        fireAllRules();
    }

    private void steroidsRosacea(CloselySupervisedPatient patient) {
        SteroidPatientEvent steroidPatientEvent = new SteroidPatientEvent(patient);
        steroidPatientEvent.setRedness(random.nextInt(5) + 1);
        steroidPatientEvent.setBurning(random.nextBoolean());
        steroidPatientEvent.setItching(random.nextBoolean());
        kieSession.insert(steroidPatientEvent);
        fireAllRules();
    }

    /*
     * ANTIFUNGAL problems
     */
    private void antifungalPeelingReaction(CloselySupervisedPatient patient) {
        AntifungalPatientEvent antifungalPatientEvent = new AntifungalPatientEvent(patient);
        antifungalPatientEvent.setRedness(random.nextInt(5) + 1);
        antifungalPatientEvent.setPeeling(random.nextBoolean());
        kieSession.insert(antifungalPatientEvent);
        fireAllRules();
    }

    private void antifungalBumpsReaction(CloselySupervisedPatient patient) {
        AntifungalPatientEvent antifungalPatientEvent = new AntifungalPatientEvent(patient);
        antifungalPatientEvent.setPimpleLikeBumps(random.nextBoolean());
        kieSession.insert(antifungalPatientEvent);
        fireAllRules();
    }

    /*
     * ANTIBIOTIC problems
     */
    private void antibioticsEczemaReaction(CloselySupervisedPatient patient) {
        AntibioticPatientEvent antibioticPatientEvent = new AntibioticPatientEvent(patient);
        antibioticPatientEvent.setEczemaLikeRash(random.nextBoolean());
        kieSession.insert(antibioticPatientEvent);
        fireAllRules();
    }

    private void antibioticsPainfulRednessReaction(CloselySupervisedPatient patient) {
        AntibioticPatientEvent antibioticPatientEvent = new AntibioticPatientEvent(patient);
        antibioticPatientEvent.setPain(random.nextInt(5) + 1);
        antibioticPatientEvent.setRedness(random.nextInt(5) + 1);
        kieSession.insert(antibioticPatientEvent);
        fireAllRules();
    }

    /* used from rools */
    public void notify(String patientName, String problem) {
        final String message = String.format("Patient %s - %s", patientName, problem);
        template.convertAndSend("/monitoring", message);
    }

    private void fireAllRules() {
        kieSession.getAgenda().getAgendaGroup("skin-monitoring").setFocus();
        kieSession.fireAllRules();
    }
}
