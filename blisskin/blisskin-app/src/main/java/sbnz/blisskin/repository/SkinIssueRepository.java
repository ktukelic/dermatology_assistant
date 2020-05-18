package sbnz.blisskin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.blisskin.model.SkinIssue;

public interface SkinIssueRepository extends JpaRepository<SkinIssue, Long> {
}
