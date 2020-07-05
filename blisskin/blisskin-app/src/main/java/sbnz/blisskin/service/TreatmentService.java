package sbnz.blisskin.service;

import org.springframework.stereotype.Service;
import sbnz.blisskin.model.Treatment;
import sbnz.blisskin.model.dto.TreatmentDTO;
import sbnz.blisskin.repository.TreatmentRepository;

import java.util.Date;

@Service
public class TreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final UserService userService;

    public TreatmentService(TreatmentRepository treatmentRepository, UserService userService) {
        this.treatmentRepository = treatmentRepository;
        this.userService = userService;
    }

    public void create(TreatmentDTO dto) {
        treatmentRepository.save(generateTreatment(dto));
    }

    private Treatment generateTreatment(TreatmentDTO dto) {
        Treatment treatment = new Treatment();
        treatment.setConsultationDate(new Date(System.currentTimeMillis()));
        treatment.setPatient(userService.findPatientById(dto.getPatientId()));
        treatment.setPrescriptionDrug(dto.getDrug());
        treatment.setRecommendedIngredients(dto.getIngredients());
        treatment.setTreatedSkinIssues(dto.getSkinIssues());

        return treatment;
    }
}
