package sbnz.blisskin.monitoring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sbnz.blisskin.model.enumerations.Drug;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CloselySupervisedPatient {

    private String name;
    private Drug prescribedDrug;
}
