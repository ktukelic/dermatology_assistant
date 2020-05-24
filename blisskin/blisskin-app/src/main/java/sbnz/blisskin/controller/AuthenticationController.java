package sbnz.blisskin.controller;

import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sbnz.blisskin.model.User;
import sbnz.blisskin.model.dto.AuthenticationRequest;
import sbnz.blisskin.service.SessionService;
import sbnz.blisskin.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        // no authentication for now
        User user = userService.findOne(loginRequest.getUsername(), loginRequest.getPassword());

        sessionService.initializeSession(user);
        sessionService.insertInitialFacts();
        return new ResponseEntity(HttpStatus.OK);
    }
}
