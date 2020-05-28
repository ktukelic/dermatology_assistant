package sbnz.blisskin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchedIngredient {

    private Ingredient ingredient;
    private float skinIssuesValue;
    private float skinPropertiesValue;
    private float ingredientDemandsValue;

    private Float total = null;

    public float getSumPercentage() {
        return this.skinIssuesValue + this.skinPropertiesValue + this.ingredientDemandsValue;
    }

    public void setTotal() {
        this.total = this.skinIssuesValue + this.skinPropertiesValue + this.ingredientDemandsValue;
    }
}
