package sbnz.blisskin.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sbnz.blisskin.model.enumerations.Role;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DERMATOLOGIST")
@Getter @Setter @NoArgsConstructor
public class Dermatologist extends User {

    public Dermatologist(String username, String password) {
        super(username, password);
        this.authorities.add(Role.DERMATOLOGIST);
    }
}
