package sbnz.blisskin.model;

import javax.persistence.*;

@Entity
@Table(name="SkinIssues")
public class SkinIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
}
