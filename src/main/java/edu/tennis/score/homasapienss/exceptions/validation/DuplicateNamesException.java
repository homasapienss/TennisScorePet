package edu.tennis.score.homasapienss.exceptions.validation;

public class DuplicateNamesException extends ValidationException {
    public DuplicateNamesException() {
        super("Игрок не может играть сам с собой");
    }
}
