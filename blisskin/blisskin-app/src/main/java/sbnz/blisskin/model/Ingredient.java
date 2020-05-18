package sbnz.blisskin.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Ingredients")
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NonNull
    private String name;

    @JoinColumn(name = "skin_properties_id", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
    private SkinProperties targetedSkinProperties;

    @ManyToMany
    @JoinColumn
    private Set<SkinIssue> targetedSkinIssues;

    // mozda grupe kao
    // moisturizers, exfoliants, potent (moze samo jedan iz grupe)

}
