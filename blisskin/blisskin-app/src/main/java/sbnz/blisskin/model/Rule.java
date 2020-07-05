package sbnz.blisskin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Rule {

    @Pattern(regexp = "^[a-zA-Z_]*$")
    private String name;
    private String lhs;
    private String rhs;
}
