package edu.tennis.score.homasapienss.exceptions.validation;

public class EmptyFormFields extends ValidationException{
    public EmptyFormFields() {
        super("Пустые поля формы");
    }
}
