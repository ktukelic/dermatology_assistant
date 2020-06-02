package sbnz.blisskin.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import sbnz.blisskin.exceptions.BadRequestException;
import sbnz.blisskin.exceptions.ForbiddenException;
import sbnz.blisskin.exceptions.NotFoundException;
import sbnz.blisskin.model.Patient;
import sbnz.blisskin.model.User;
import sbnz.blisskin.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User findCurrentUser() {
        final UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null)
            throw new ForbiddenException("You don't have permission to access this method on the server.");

        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return this.findByUsername(userDetails.getUsername());
    }

    public User savePatient(Patient patient) {
        checkIfUsernameTaken(patient.getUsername());
        return userRepository.save(patient);
    }

    public void checkIfUsernameTaken(String username) {
        this.userRepository.findByUsernameIgnoreCase(username)
                .ifPresent(u -> {
                    throw new BadRequestException(String.format("Username '%s' is already taken", username));
                });
    }

}
