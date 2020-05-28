package sbnz.blisskin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sbnz.blisskin.model.enumerations.Assessment;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class SkinProperties {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Assessment moisture;

    @Column
    private Assessment sebum;

    @Column
    private Assessment sensitivity;

    public SkinProperties() {
        this.moisture = null;
        this.sebum = null;
        this.sensitivity = null;
    }



}
