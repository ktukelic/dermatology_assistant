package sbnz.blisskin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.blisskin.model.Patient;
import sbnz.blisskin.model.dto.PatientDTO;
import sbnz.blisskin.service.UserService;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity findPatient(@RequestParam("username") String username) {
        Patient patient = (Patient) userService.findByUsername(username);
        return new ResponseEntity(patient, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createPatient(@RequestBody PatientDTO patientDTO) {
        Patient patient = (Patient) userService.createPatient(patientDTO.convertToEntity());
        return new ResponseEntity(patient, HttpStatus.CREATED);
    }
}
