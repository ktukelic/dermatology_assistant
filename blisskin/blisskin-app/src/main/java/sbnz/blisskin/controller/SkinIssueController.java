package sbnz.blisskin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sbnz.blisskin.model.SkinIssue;
import sbnz.blisskin.service.SkinIssueService;

@RestController
@RequestMapping("/api/skin-issues")
public class SkinIssueController {

    private final SkinIssueService skinIssueService;

    public SkinIssueController(SkinIssueService skinIssueService) {
        this.skinIssueService = skinIssueService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('DERMATOLOGIST')")
    public ResponseEntity findAll() {
        return new ResponseEntity(skinIssueService.findAll(), HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DERMATOLOGIST')")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        return new ResponseEntity(skinIssueService.findAll(), HttpStatus.FOUND);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity create(@RequestBody SkinIssue skinIssue) {
        return new ResponseEntity(skinIssueService.save(skinIssue), HttpStatus.CREATED);
    }

    // ToDo update skin issue (admin)
    // ToDo delete skinIssue (admin)

}
