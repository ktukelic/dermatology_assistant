package sbnz.blisskin.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter @Setter @NoArgsConstructor
public class Dermatologist extends User {

    public Dermatologist(String username, String password) {
        super(username, password);
        this.setRole(Role.DERMATOLOGIST);
    }
}
