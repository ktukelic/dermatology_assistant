package sbnz.blisskin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends User {

    @Column
    private UUID patientId;

    @Column
    private Integer age;

    @OneToMany
    private Set<Treatment> previousTreatments;

    @OneToMany
    private Set<Ingredient> ingredientReactions;


}
