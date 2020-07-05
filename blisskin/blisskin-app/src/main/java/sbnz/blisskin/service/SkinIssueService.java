package sbnz.blisskin.service;

import org.springframework.stereotype.Service;
import sbnz.blisskin.exceptions.NotFoundException;
import sbnz.blisskin.model.SkinIssue;
import sbnz.blisskin.repository.SkinIssueRepository;

import java.util.List;

@Service
public class SkinIssueService {

    private final SkinIssueRepository skinIssueRepository;

    public SkinIssueService(SkinIssueRepository skinIssueRepository) {
        this.skinIssueRepository = skinIssueRepository;
    }

    public List<SkinIssue> findAll() {
        return skinIssueRepository.findAll();
    }

    public SkinIssue findById(Long id) {
        return skinIssueRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Skin Issue not found"));
    }

    public SkinIssue save(SkinIssue skinIssue) {
        return skinIssueRepository.save(skinIssue);
    }

    public void delete(Long id) {
        skinIssueRepository.delete(findById(id));
    }

}
