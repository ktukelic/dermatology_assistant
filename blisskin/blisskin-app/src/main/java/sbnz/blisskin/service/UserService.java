package sbnz.blisskin.service;

import org.springframework.stereotype.Service;
import sbnz.blisskin.exceptions.BadRequestException;
import sbnz.blisskin.exceptions.NotFoundException;
import sbnz.blisskin.model.Dermatologist;
import sbnz.blisskin.model.Patient;
import sbnz.blisskin.model.User;
import sbnz.blisskin.repository.PatientRepository;
import sbnz.blisskin.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;

    public UserService(UserRepository userRepository, PatientRepository patientRepository) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public Patient findPatientById(Long id) {
        return patientRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Patient with id %d not found", id)));
    }

    public User savePatient(Patient patient) {
        checkIfUsernameTaken(patient.getUsername());
        return userRepository.save(patient);
    }

    public User saveDerm(Dermatologist dermatologist) {
        checkIfUsernameTaken(dermatologist.getUsername());
        return userRepository.save(dermatologist);
    }

    private void checkIfUsernameTaken(String username) {
        this.userRepository.findByUsernameIgnoreCase(username).ifPresent(u -> {
            throw new BadRequestException(String.format("Username '%s' is already taken", username));
        });
    }
}
