package sbnz.blisskin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sbnz.blisskin.model.SkinIssue;
import sbnz.blisskin.model.SkinProperties;
import sbnz.blisskin.model.enumerations.Assessment;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TreatmentRequest {

    private Integer patientAge;
    private Assessment humidity;
    private Assessment sunExposure;

    private SkinProperties skinProperties;
    private List<SkinIssue> skinIssues;


}
