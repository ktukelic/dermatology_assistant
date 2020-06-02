package sbnz.blisskin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import sbnz.blisskin.config.security.TokenUtils;
import sbnz.blisskin.exceptions.AuthorizationException;
import sbnz.blisskin.model.User;
import sbnz.blisskin.model.dto.AuthenticationRequest;
import sbnz.blisskin.model.dto.AuthenticationResponse;
import sbnz.blisskin.service.UserService;

import javax.validation.Valid;

@RestController @RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final TokenUtils tokenUtils;

    @PostMapping
    public ResponseEntity authenticate(@Valid @RequestBody AuthenticationRequest authenticationRequest) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );

            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final User user = userService.findByUsername(userDetails.getUsername());

            if(user.getAuthorities().isEmpty())
                throw new AuthorizationException("User not verified.");

            final String token = tokenUtils.generateToken(userDetails);
            return new ResponseEntity<>(new AuthenticationResponse(token), HttpStatus.OK);

        } catch (BadCredentialsException ex) {
            throw new AuthorizationException("User credentials invalid.");
        }
    }

}
