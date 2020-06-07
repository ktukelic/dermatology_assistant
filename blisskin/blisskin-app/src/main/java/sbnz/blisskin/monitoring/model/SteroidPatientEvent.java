package sbnz.blisskin.monitoring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.type.Role;

import javax.validation.constraints.Size;

@Role(Role.Type.EVENT)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SteroidPatientEvent {

    private CloselySupervisedPatient patient;

    private boolean purpura;
    private boolean bruising;

    @Size(min = 1, max = 5)
    private int redness;
    private boolean itching;
    private boolean burning;

    public SteroidPatientEvent(CloselySupervisedPatient patient) {
        this.patient = patient;
    }

}
