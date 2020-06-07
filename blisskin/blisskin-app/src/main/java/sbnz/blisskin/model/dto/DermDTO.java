package sbnz.blisskin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sbnz.blisskin.model.Dermatologist;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DermDTO {

    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public Dermatologist convertToEntity() {
        Dermatologist derm = new Dermatologist(username, password);
        derm.setFirstName(firstName);
        derm.setLastName(lastName);
        return derm;
    }
}
