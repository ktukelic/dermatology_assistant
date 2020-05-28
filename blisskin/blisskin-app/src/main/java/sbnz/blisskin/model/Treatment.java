package sbnz.blisskin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="Treatments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Date consultationDate;

    @ManyToOne
    private Patient patient;

    @ManyToMany
    private Set<Ingredient> recommendedIngredients;

    @ManyToOne
    @JoinColumn
    private PrescriptionDrug prescriptionDrug;

//    @OneToMany
//    @JoinColumn
//    private Set<SkinIssue> treatedSkinIssues;
}
