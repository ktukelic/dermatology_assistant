package sbnz.blisskin.service;

import org.springframework.stereotype.Service;
import sbnz.blisskin.exceptions.NotFoundException;
import sbnz.blisskin.model.User;
import sbnz.blisskin.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findOne(String username, String password) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }
}
