package sbnz.blisskin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sbnz.blisskin.model.enumerations.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("PATIENT")
@Getter
@Setter
@AllArgsConstructor
public class Patient extends User {

    @Column
    private Integer age;

    @OneToMany(cascade = CascadeType.ALL)
//    @JsonIgnore
    private Set<Treatment> previousTreatments;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Ingredient> ingredientReactions;

    public Patient() {
        this.previousTreatments = new HashSet<>();
        this.ingredientReactions = new HashSet<>();
        this.authorities.add(Role.PATIENT);
    }

    public Patient(String firstName, String lastName, String username, String password, Integer age) {
        super(firstName, lastName, username, password);
        this.age = age;
        this.previousTreatments = new HashSet<>();
        this.ingredientReactions = new HashSet<>();
        this.authorities.add(Role.PATIENT);
    }


}
