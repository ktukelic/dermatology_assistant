package sbnz.blisskin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SkinProperties {

    public enum Assessment {
        VERY_LOW, LOW, NORMAL, HIGH, VERY_HIGH
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Assessment moisture;

    @Column
    private Assessment elasticity;

    @Column
    private Assessment sebum;

    @Column
    private Assessment sensitivity;

}
