package sbnz.blisskin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sbnz.blisskin.model.SkinIssue;
import sbnz.blisskin.model.dto.TreatmentDTO;
import sbnz.blisskin.model.dto.TreatmentRequest;
import sbnz.blisskin.service.ReasoningService;
import sbnz.blisskin.service.TreatmentService;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('DERMATOLOGIST')")
@RequestMapping("/api/treatment")
public class TreatmentController {

    private final ReasoningService reasoningService;
    private final TreatmentService treatmentService;

    public TreatmentController(ReasoningService reasoningService, TreatmentService treatmentService) {
        this.reasoningService = reasoningService;
        this.treatmentService = treatmentService;
    }

    @PostMapping
    public ResponseEntity findBestTreatment(@RequestBody TreatmentRequest treatmentRequest) {
        return new ResponseEntity(reasoningService.findBestTreatment(treatmentRequest), HttpStatus.OK);
    }

    @PostMapping("/ingredients")
    public ResponseEntity findIngredientsForSkinIssues(@RequestBody List<SkinIssue> skinIssues) {
        return new ResponseEntity(reasoningService.findIngredientsForSkinIssues(skinIssues), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity createTreatment(@RequestBody TreatmentDTO dto) {
        treatmentService.create(dto);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
