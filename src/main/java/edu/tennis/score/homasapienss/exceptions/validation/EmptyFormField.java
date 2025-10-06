package edu.tennis.score.homasapienss.exceptions.validation;

public class EmptyFormField extends  ValidationException{
    public EmptyFormField() {
        super("Пустое поле формы");
    }
}
