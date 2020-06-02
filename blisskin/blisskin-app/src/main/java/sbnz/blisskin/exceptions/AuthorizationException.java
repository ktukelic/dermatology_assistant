package sbnz.blisskin.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AuthorizationException extends RuntimeException {

    public AuthorizationException(String message) {
        super(message);
    }
}
