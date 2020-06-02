package sbnz.blisskin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;
import sbnz.blisskin.model.enumerations.Drug;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Role(Role.Type.EVENT)
@Timestamp("consultationDate")
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

    @Enumerated(EnumType.STRING)
    private Drug prescriptionDrug;

    @ManyToMany
    private Set<Ingredient> recommendedIngredients;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn
    private Set<SkinIssue> treatedSkinIssues;

    public boolean isIssueTreated(String skinIssueName) {
        for(SkinIssue issue : treatedSkinIssues) {
            if (issue.getName().equals(skinIssueName)) {
                return true;
            }
        }
        return false;
    }
}
