package sbnz.blisskin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sbnz.blisskin.model.Patient;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PatientDTO {

    private String firstName;
    private String lastName;
    private String username;
    private String password;

    private Integer age;

    public Patient convertToEntity() {
        return new Patient(firstName, lastName, username, password, age);
    }
}
