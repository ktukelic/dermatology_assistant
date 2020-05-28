package sbnz.blisskin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sbnz.blisskin.model.dto.TreatmentRequest;
import sbnz.blisskin.model.dto.TreatmentResponse;
import sbnz.blisskin.service.ReasoningService;

@RestController
@RequestMapping("/api/treatment")
public class TreatmentController {

    private final ReasoningService reasoningService;

    public TreatmentController(ReasoningService reasoningService) {
        this.reasoningService = reasoningService;
    }

    @PostMapping
    public ResponseEntity findBestTreatment(@RequestBody TreatmentRequest treatmentRequest) {
        TreatmentResponse recommendedTreatment = reasoningService.findBestTreatment(treatmentRequest);
        return new ResponseEntity(recommendedTreatment, HttpStatus.OK);

    }
}
