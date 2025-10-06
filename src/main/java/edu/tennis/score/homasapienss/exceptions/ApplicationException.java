package edu.tennis.score.homasapienss.exceptions;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

    private final String exceptionMessage;

    public ApplicationException(String message) {
        super(message);
        exceptionMessage = message;
    }

}
