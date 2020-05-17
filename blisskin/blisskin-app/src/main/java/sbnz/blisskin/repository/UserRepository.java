package sbnz.blisskin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.blisskin.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
