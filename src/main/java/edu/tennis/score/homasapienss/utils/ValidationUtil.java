package edu.tennis.score.homasapienss.utils;

import edu.tennis.score.homasapienss.exceptions.validation.DuplicateNamesException;
import edu.tennis.score.homasapienss.exceptions.validation.EmptyFormField;
import edu.tennis.score.homasapienss.exceptions.validation.EmptyFormFields;

import java.util.Objects;

public class ValidationUtil {
    public static void validateNames(String nameOne, String nameTwo) {
        if (nameOne.isBlank() && nameTwo.isBlank()){
            throw new EmptyFormFields();
        }
        if (nameOne.isBlank() || nameTwo.isBlank()){
            throw new EmptyFormField();
        }
        if (Objects.equals(nameOne, nameTwo)) {
            throw new DuplicateNamesException();
        }
    }
}
