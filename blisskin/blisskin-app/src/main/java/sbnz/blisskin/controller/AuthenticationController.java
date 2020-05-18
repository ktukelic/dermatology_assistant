package sbnz.blisskin.controller;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sbnz.blisskin.model.User;
import sbnz.blisskin.model.dto.AuthenticationRequest;
import sbnz.blisskin.service.SessionService;
import sbnz.blisskin.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final SessionService sessionService;
    private final UserService userService;

    @Autowired
    public AuthenticationController(SessionService sessionService, UserService userService) {
        this.sessionService = sessionService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody AuthenticationRequest loginRequest) {
        // no validation for now
        User user = userService.findOne(loginRequest.getUsername(), loginRequest.getPassword());

        KieSession kSession = sessionService.initializeSession();
        sessionService.insertInitialFacts(kSession);
        return new ResponseEntity(HttpStatus.OK);
    }
}
