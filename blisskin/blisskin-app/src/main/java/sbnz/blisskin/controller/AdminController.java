package sbnz.blisskin.controller;

import org.apache.maven.shared.invoker.MavenInvocationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sbnz.blisskin.model.Rule;
import sbnz.blisskin.service.RuleService;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/api/admin")
public class AdminController {

    private final RuleService ruleService;

    public AdminController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping(value = "/rules/add", produces = "text/plain")
    public ResponseEntity addNewRule(@RequestBody Rule rule) throws MavenInvocationException, IllegalAccessException, InstantiationException {
        return new ResponseEntity(ruleService.addNewRule(rule), HttpStatus.OK);
    }
}
