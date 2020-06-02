package sbnz.blisskin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sbnz.blisskin.model.SkinIssue;
import sbnz.blisskin.model.dto.TreatmentRequest;
import sbnz.blisskin.service.ReasoningService;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('DERMATOLOGIST')")
@RequestMapping("/api/treatment")
public class TreatmentController {

    private final ReasoningService reasoningService;

    public TreatmentController(ReasoningService reasoningService) {
        this.reasoningService = reasoningService;
    }

    @PostMapping
    public ResponseEntity findBestTreatment(@RequestBody TreatmentRequest treatmentRequest) {
        return new ResponseEntity(reasoningService.findBestTreatment(treatmentRequest), HttpStatus.OK);
    }

    @PostMapping("/ingredients")
    public ResponseEntity findIngredientsForSkinIssues(@RequestBody List<SkinIssue> skinIssues) {
        return new ResponseEntity(reasoningService.findIngredientsForSkinIssues(skinIssues), HttpStatus.OK);
    }
}
