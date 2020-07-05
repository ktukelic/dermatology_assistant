package sbnz.blisskin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sbnz.blisskin.model.MatchedIngredient;
import sbnz.blisskin.model.enumerations.Drug;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TreatmentResponse {

    private MatchedIngredient hydration;
    private MatchedIngredient exfoliant;
    private MatchedIngredient antioxidant;
    private MatchedIngredient skinRestoring;

    private Drug drug = null;
}
