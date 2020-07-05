package sbnz.blisskin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sbnz.blisskin.model.SkinIssue;
import sbnz.blisskin.service.SkinIssueService;

@RestController
@RequestMapping("/api/issues")
public class SkinIssueController {

    private final SkinIssueService skinIssueService;

    public SkinIssueController(SkinIssueService skinIssueService) {
        this.skinIssueService = skinIssueService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('DERMATOLOGIST')")
    public ResponseEntity findAll() {
        return new ResponseEntity(skinIssueService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('DERMATOLOGIST')")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        return new ResponseEntity(skinIssueService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity create(@RequestBody SkinIssue skinIssue) {
        return new ResponseEntity(skinIssueService.save(skinIssue), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        skinIssueService.delete(id);
    }
}
