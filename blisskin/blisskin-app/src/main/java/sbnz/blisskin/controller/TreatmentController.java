package sbnz.blisskin.controller;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sbnz.blisskin.model.dto.TreatmentRequest;
import sbnz.blisskin.service.SessionService;

@RestController
@RequestMapping("/api/treatment")
public class TreatmentController {

    private final SessionService sessionService;

    public TreatmentController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping
    public ResponseEntity findBestTreatment(@RequestBody TreatmentRequest treatmentRequest){
        KieSession kieSession = sessionService.getKieSession();
        kieSession.insert(treatmentRequest);
        FactHandle fh = kieSession.getFactHandle(treatmentRequest);
        kieSession.fireAllRules();
        return new ResponseEntity(HttpStatus.OK);

    }
}
