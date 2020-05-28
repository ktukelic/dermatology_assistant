package sbnz.blisskin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import sbnz.blisskin.model.User;
import sbnz.blisskin.model.dto.AuthenticationRequest;
import sbnz.blisskin.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody AuthenticationRequest loginRequest) {
        // no authentication for now
        User user = userService.findOne(loginRequest.getUsername(), loginRequest.getPassword());
        userService.setCurrentUser(user);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity logout() {
        SecurityContextHolder.clearContext();
        return new ResponseEntity(HttpStatus.OK);
    }
}
