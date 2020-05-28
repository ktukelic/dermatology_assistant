package sbnz.blisskin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sbnz.blisskin.model.enumerations.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends User {

    @Column
    private Integer age;

    @OneToMany
    private Set<Treatment> previousTreatments;

    @OneToMany
    private Set<Ingredient> ingredientReactions;

    public Patient(String firstName, String lastName, String username, String password, Integer age) {
        super(firstName, lastName, username, password);
        this.age = age;
        this.setRole(Role.PATIENT);
    }


}
