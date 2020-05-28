package sbnz.blisskin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sbnz.blisskin.model.MatchedIngredient;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TreatmentResponse {

    // ToDo naci sta je potrebno na frontu, ostalo da se ne salje
    private MatchedIngredient hydration;
    private MatchedIngredient exfoliant;
    private MatchedIngredient antioxidant;
    private MatchedIngredient skinRestoring;
}
