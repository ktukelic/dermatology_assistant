package sbnz.blisskin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.blisskin.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    <E extends User> Optional<E> findByUsernameIgnoreCase(String username);
    <E extends User> Optional<E> findByUsername(String username);
    <E extends User> E save(E user);
}
