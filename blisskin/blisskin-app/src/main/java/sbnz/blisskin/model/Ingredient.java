package sbnz.blisskin.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @JoinColumn(name = "skin_properties_id", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
    private SkinProperties targetedSkinProperties;

    @ManyToMany
    @JoinColumn
    private Set<SkinIssue> targetedSkinIssues;

}
