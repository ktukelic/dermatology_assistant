package sbnz.blisskin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sbnz.blisskin.model.enumerations.Drug;
import sbnz.blisskin.service.ReasoningService;

@RestController
@PreAuthorize("hasAuthority('DERMATOLOGIST')")
@RequestMapping("/api/reports")
public class ReportController {

    private final ReasoningService reasoningService;

    public ReportController(ReasoningService reasoningService) {
        this.reasoningService = reasoningService;
    }

    @GetMapping
    public ResponseEntity getReports(@RequestParam("drug") Drug drugType) {
        return new ResponseEntity(reasoningService.getReports(drugType), HttpStatus.OK);
    }
}
