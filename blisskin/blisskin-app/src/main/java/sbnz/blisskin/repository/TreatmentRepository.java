package sbnz.blisskin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.blisskin.model.Treatment;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
}
