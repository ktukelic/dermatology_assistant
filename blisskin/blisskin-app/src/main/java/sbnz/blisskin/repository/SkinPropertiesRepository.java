package sbnz.blisskin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.blisskin.model.SkinProperties;

public interface SkinPropertiesRepository extends JpaRepository<SkinProperties, Long> {
}
