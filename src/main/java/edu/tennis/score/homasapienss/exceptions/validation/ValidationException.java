package edu.tennis.score.homasapienss.exceptions.validation;

import edu.tennis.score.homasapienss.exceptions.ApplicationException;

public class ValidationException extends ApplicationException {
    public ValidationException(String message) {
        super(message);
    }
}
