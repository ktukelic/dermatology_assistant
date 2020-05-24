package sbnz.blisskin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sbnz.blisskin.model.enumerations.DrugType;

import javax.persistence.*;

@Entity
@Table(name = "PrescriptionDrugs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PrescriptionDrug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    private DrugType drugType;

}
