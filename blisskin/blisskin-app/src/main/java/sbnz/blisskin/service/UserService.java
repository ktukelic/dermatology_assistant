package sbnz.blisskin.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import sbnz.blisskin.exceptions.BadRequestException;
import sbnz.blisskin.exceptions.NotFoundException;
import sbnz.blisskin.model.Patient;
import sbnz.blisskin.model.User;
import sbnz.blisskin.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findOne(String username, String password) {
        // ToDo dodati proveru lozinke
        return findByUsername(username);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public void setCurrentUser(User user) {
        final Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
        final Authentication authentication = new PreAuthenticatedAuthenticationToken(user, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public User createPatient(Patient patient) {
        checkIfUsernameIsTaken(patient.getUsername());
        return userRepository.save(patient);
    }

    private void checkIfUsernameIsTaken(String username) {
        this.userRepository.findByUsername(username).ifPresent(u -> {
            throw new BadRequestException(String.format("Username '%s' is already taken", username));
        });
    }
}
