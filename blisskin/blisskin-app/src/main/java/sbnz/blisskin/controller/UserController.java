package sbnz.blisskin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sbnz.blisskin.model.Dermatologist;
import sbnz.blisskin.model.Patient;
import sbnz.blisskin.model.dto.DermDTO;
import sbnz.blisskin.model.dto.PatientDTO;
import sbnz.blisskin.service.UserService;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    // ToDo find all users (for admin)

    @GetMapping
    public ResponseEntity findPatient(@RequestParam("username") String username) {
        Patient patient = (Patient) userService.findByUsername(username);
        return new ResponseEntity(patient, HttpStatus.OK);
    }

    @PostMapping(value = "/patient")
    @PreAuthorize("hasAuthority('DERMATOLOGIST')")
    public ResponseEntity createPatient(@RequestBody PatientDTO patientDTO) {
        patientDTO.setPassword(passwordEncoder.encode(patientDTO.getPassword()));
        Patient patient = (Patient) userService.savePatient(patientDTO.convertToEntity());
        return new ResponseEntity(patient, HttpStatus.CREATED);
    }

    @PostMapping(value = "/derm")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity createDermatologist(@RequestBody DermDTO dermDTO) {
        dermDTO.setPassword(passwordEncoder.encode(dermDTO.getPassword()));
        Dermatologist derm = (Dermatologist) userService.saveDerm(dermDTO.convertToEntity());
        return new ResponseEntity(derm, HttpStatus.CREATED);
    }
}
