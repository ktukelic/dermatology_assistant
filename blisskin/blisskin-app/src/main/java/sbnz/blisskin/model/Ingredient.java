package sbnz.blisskin.model;

import lombok.*;
import sbnz.blisskin.model.enumerations.IngredientGroup;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NonNull
    private String name;

    @Column
    @NonNull
    private IngredientGroup ingredientGroup;

    @JoinColumn(name = "skin_properties_id")
    @OneToOne(cascade = CascadeType.ALL)
    private SkinProperties notRecommendedSkinProperties;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn
    private Set<SkinIssue> targetedSkinIssues;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn
    private Set<IngredientDemand> ingredientDemands;


}
