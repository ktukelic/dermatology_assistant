package sbnz.blisskin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sbnz.blisskin.model.Ingredient;
import sbnz.blisskin.model.SkinIssue;
import sbnz.blisskin.model.enumerations.Drug;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentDTO {

    private Long patientId;
    private Set<Ingredient> ingredients;
    private Set<SkinIssue> skinIssues;
    private Drug drug;
}
