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
public class AntibioticPatientEvent {

    private CloselySupervisedPatient patient;

    @Size(min = 1, max = 5)
    private int redness;
    @Size(min = 1, max = 5)
    private int pain;

    private boolean eczemaLikeRash;

    public AntibioticPatientEvent(CloselySupervisedPatient patient) {
        this.patient = patient;
    }
}
